package geektime.spring.springbucks.bean.model;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class Prodcut {
	@Value ("CP001")
	private String prdCode;
	@Value ("重疾险")
	private String prdName;

	@Override
	public String toString () {
		return "Prodcut{" +
				"prdCode='" + prdCode + '\'' +
				", prdName='" + prdName + '\'' +
				'}';
	}
}
