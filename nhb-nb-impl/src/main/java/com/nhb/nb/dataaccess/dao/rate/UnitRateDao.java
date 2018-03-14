package com.nhb.nb.dataaccess.dao.rate;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.UnitRate;

@Repository
public interface UnitRateDao extends MongoRepository<UnitRate, String> {

	List<UnitRate> findByAreaId(String areaId, Sort sort);

	List<UnitRate> findByAreaIdAndStartDateAfterAndEndDateBefore(String areaId, String today, String today2, Sort sort);

	List<UnitRate> findByAreaIdAndStartDateLessThanAndEndDateGreaterThan(String areaId, String today, String today2,
			Sort sort);

	List<UnitRate> findByAreaIdIn(List<String> areaIds);

	List<UnitRate> findByStartDateLessThanEqualAndEndDateGreaterThan(String day, String day2);

}
