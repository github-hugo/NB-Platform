package com.nhb.nb.dataaccess.service.rate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.rate.UnitRateDao;
import com.nhb.nb.dataaccess.entity.UnitRate;

@Service
public class UnitRateService {

	@Autowired
	private UnitRateDao unitRateDao;

	public UnitRate saveOrUpdate(UnitRate unitRate) {
		return unitRateDao.save(unitRate);
	}

	public UnitRate findById(String areaId) {
		return unitRateDao.findOne(areaId);
	}

	public List<UnitRate> findByAreaId(String areaId, Sort sort) {
		return unitRateDao.findByAreaId(areaId, sort);
	}

	public List<UnitRate> findByAreaIdAndStartDateAfterAndEndDateBefore(String areaId, String today, String today2,
			Sort sort) {
		return unitRateDao.findByAreaIdAndStartDateAfterAndEndDateBefore(areaId, today, today2, sort);
	}

	public List<UnitRate> findByAreaIdAndStartDateLessThanAndEndDateGreaterThan(String areaId, String today,
			String today2, Sort sort) {
		return unitRateDao.findByAreaIdAndStartDateLessThanAndEndDateGreaterThan(areaId, today, today2, sort);
	}

	public List<UnitRate> findByAreaIdIn(List<String> areaIds) {
		return unitRateDao.findByAreaIdIn(areaIds);
	}

	public List<UnitRate> findByStartDateLessThanEqualAndEndDateGreaterThan(String day, String day2) {
		return unitRateDao.findByStartDateLessThanEqualAndEndDateGreaterThan(day, day2);
	}

}
