package geektime.spring.springbucks;

import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.redis.core.*;
import org.springframework.util.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

@Slf4j
@SpringBootApplication
public class SpringRedisApplication implements ApplicationRunner {

	final static String KEY = "stu";
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static final long BEGIN_TIMESTAMP = 1640995200L;

	private static final int COUNT_BITS = 32;

	public static void main (String[] args) {
		SpringApplication.run (SpringRedisApplication.class, args);
	}


	@Override
	public void run (ApplicationArguments args) {
		/*
		实现分数排名或者排行榜；
		 */
		//批量增加
		Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("LiLi" + i, 1D + i);
			tuples.add(tuple);
		}
		redisTemplate.opsForZSet ().add (KEY,tuples);

		//获取分数排名
//		逆序获取对应下标的元素
		Set<String> strings = redisTemplate.opsForZSet ().reverseRange (KEY, 0, - 1);
		System.out.println ("strings = " + strings);

		//获取指定score区间的值
		Set<String> strings1 = redisTemplate.opsForZSet ().rangeByScore (KEY, 3D, 7D);
		System.out.println ("strings1 = " + strings1);

//		倒序排序获取RedisZSetCommands.Tuples的分值区间值。
		Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet ().reverseRangeByScoreWithScores (KEY, 2D, 6D);
		if(!CollectionUtils.isEmpty (typedTuples)) {
			for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
				System.out.println ("name: " + typedTuple.getValue () + "分数: " + typedTuple.getScore ());
			}
		}

		Set<String> strings2 = redisTemplate.opsForZSet ().reverseRangeByScore (KEY, 2D, 6D);
		System.out.println ("strings2 = " + strings2);


		/*
		1、雪花算法
		2、redis实现全局 ID 生成；（这里用redis自增）
		 */

		for (int i = 0; i < 10; i++) {
			System.out.println ("nextId (\"order\") = " + nextId ("order"));
		}


		/*
		基于 Bitmap 实现 id 去重；
	    */
		List<Integer> list = new ArrayList<> ();
		list.add(0);
		list.add(1);
		list.add(1);
		list.add(0);
		list.add(1);

		List<Integer> resList = new ArrayList<> ();
		for(Integer integer :list) {
			if (redisTemplate.opsForValue ().getBit ("key1", integer)) {continue;}
			resList.add (integer);
			redisTemplate.opsForValue ().setBit ("key1", integer, true);

		}
		System.out.println("resList = "+resList);

		/*
		基于 HLL 实现点击量计数
		 */
		String key = "hhl";
		redisTemplate.opsForHyperLogLog ().add (key,"AA","BB","AA","CC","BB","SS","AA");
		Long size = redisTemplate.opsForHyperLogLog ().size (key);
		System.out.println ("size = " + size);
	}

	public Long nextId(String keyPrefix) {
		//获取当前时间
		LocalDateTime now = LocalDateTime.now();
		//获取当前时间得秒数
		long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
		long time = nowSecond - BEGIN_TIMESTAMP;

		String format = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
		// Redis Incrby 命令将 key 中储存的数字加上指定的增量值。
		// 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
		Long count = redisTemplate.opsForValue().increment("incr:" + keyPrefix + ":" + format);

		return time << COUNT_BITS | count;
	}





}

