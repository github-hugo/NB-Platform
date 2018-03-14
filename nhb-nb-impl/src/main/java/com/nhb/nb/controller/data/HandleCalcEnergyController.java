package com.nhb.nb.controller.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.nhb.nb.dataaccess.entity.DeviceCircuit;
import com.nhb.nb.dataaccess.entity.DeviceInfo;
import com.nhb.nb.dataaccess.entity.SysArea;
import com.nhb.nb.dataaccess.service.area.SysAreaService;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.dataaccess.service.device.DeviceService;
import com.nhb.nb.job.CalcEnergyJob;
import com.nhb.nb.request.data.EnergyDataRequest;
import com.nhb.utils.nhb_utils.common.DateUtil;
import com.nhb.utils.nhb_utils.common.RestResultDto;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("nbiot/v1/handle")
public class HandleCalcEnergyController {

	@Autowired
	private CalcEnergyJob calcEnergyJob;

	@Autowired
	private SysAreaService sysAreaService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceCircuitService deviceCircuitService;

	/**
	 * @return RestResultDto
	 * @Title: handleEnergy
	 * @Description: 获取日能耗数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "手动计算日能耗", notes = "手动计算日能耗，日期，和日期的开始和结束时间")
	@RequestMapping(value = "handleEnergy", method = { RequestMethod.POST })
	public RestResultDto handleEnergy(@RequestBody EnergyDataRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {

			String tenantId = request.getTenantId();
			String date = request.getDate();
			String month = date.substring(0, 7);

			Date startTime = DateUtil.parse(date + " 00:00:00", DateUtil.DATETIME_FORMAT);
			Date endTime = DateUtil.parse(date + " 23:59:59", DateUtil.DATETIME_FORMAT);

			SimpleDateFormat lastDayFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date d = lastDayFormat.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			calendar.add(Calendar.DATE, -1);
			String lastDay = lastDayFormat.format(calendar.getTime());
			Date lastStartTime = DateUtil.parse(lastDay + " 00:00:00", DateUtil.DATETIME_FORMAT);
			Date lastEndTime = DateUtil.parse(lastDay + " 23:59:59", DateUtil.DATETIME_FORMAT);

			// SysUser sysUser = sysUserService.findByUserId(tenantId);
			List<String> deviceIds = Lists.newArrayList();
			List<String> areaIds = Lists.newArrayList();
			// 当前用户下属所有区域id
			List<SysArea> sysAreas = sysAreaService.findByTenantId(tenantId);
			for (SysArea sysArea : sysAreas) {
				areaIds.add(sysArea.getAreaId());
			}
			List<DeviceInfo> deviceInfos = deviceService.findByEndUserIdIn(areaIds);
			for (DeviceInfo deviceInfo : deviceInfos) {
				deviceIds.add(deviceInfo.getDeviceId());
			}

			List<String> circuitIds = Lists.newArrayList();
			List<DeviceCircuit> deviceCircuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
			for (DeviceCircuit circuit : deviceCircuits) {
				circuitIds.add(circuit.getCircuitId());
			}

			calcEnergyJob.deleteData(month, date, circuitIds);
			calcEnergyJob.calcData(month, date, circuitIds, deviceCircuits, startTime, endTime, lastStartTime,
					lastEndTime);

			data = true;
			msg = "计算成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			data = null;
			msg = "计算失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;

	}

}
