package geektime.spring.springbucks.filter;

import com.alibaba.druid.filter.*;
import com.alibaba.druid.proxy.jdbc.*;
import com.alibaba.druid.sql.*;
import com.alibaba.druid.sql.ast.*;
import com.alibaba.druid.sql.dialect.mysql.parser.*;
import com.alibaba.druid.sql.dialect.mysql.visitor.*;
import com.alibaba.druid.stat.*;
import lombok.extern.slf4j.*;
import org.springframework.util.*;

import java.sql.*;
import java.util.*;


@Slf4j
public class ConnectionFilter extends FilterEventAdapter {

	public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
		List<SQLStatement> sqlStatements = SQLUtils.parseStatements (statement.getSql (),"mysql");
		if(! CollectionUtils.isEmpty (sqlStatements)){
			if(10 < sqlStatements.size ()){
				throw new SQLException (String.format ("SQL 分割条数为 %s,超过10条限制", Integer.valueOf (sqlStatements.size ())));
			}

			MySqlSchemaStatVisitor statVisitor = new MySqlSchemaStatVisitor ();
			for (SQLStatement sqlStatement : sqlStatements) {
				sqlStatement.accept (statVisitor);
				for (TableStat.Condition condition : statVisitor.getConditions ()) {
					if(CollectionUtils.isEmpty (condition.getValues ()) || 10 > condition.getValues ().size ()){
						continue;
					}
					throw new SQLException(String.format ("入参个数：%s,超过10条限制",
							Integer.valueOf (condition.getValues ().size ())));
				}
			}

		}
		return super.preparedStatement_execute (chain,statement);
	}
}
