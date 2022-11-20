package geektime.spring.springbucks.service;

import geektime.spring.springbucks.mapper.*;
import geektime.spring.springbucks.model.*;
import lombok.extern.slf4j.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@CacheConfig(cacheNames = "coffee-cache")
@Service
public class CoffeeService {

	@Autowired
	private CoffeeMapper coffeeMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveCoffee (Coffee coffee) {
	    coffeeMapper.insert (coffee);
    }

    @Cacheable(key = "'allCoffee'",unless = "#result.size() == 0")
    public List<Coffee> getAllCoffee (CoffeeExample example) {
        return coffeeMapper.selectByExample (example);
    }

	public List<Coffee> batchCoffee (CoffeeExample example) {
		return coffeeMapper.selectByExample (example);
	}

    @CacheEvict(cacheNames = "coffee-cache",key = "'allCoffee'")
    public void reloadCoffee () {
        log.info ("清除所有缓存");
    }


    @Cacheable(value = "coffee-cache",key = "#id")
    public Coffee getCoffeeById (long id) {
      return coffeeMapper.selectByPrimaryKey (id);
    }


	public List<Coffee> findCoffeeByPage(CoffeeExample example, RowBounds rowBounds){
		return coffeeMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteCoffee(Long id){
		coffeeMapper.deleteByPrimaryKey (id);
	}


	@CachePut(key = "#coffee.id")
	@Transactional(rollbackFor = Exception.class)
	public Coffee updateCoffee(Coffee coffee ){
		coffeeMapper.updateByPrimaryKey (coffee);
		return coffee;
	}



}
