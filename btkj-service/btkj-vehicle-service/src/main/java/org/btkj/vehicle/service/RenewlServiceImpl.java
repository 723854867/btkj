package org.btkj.vehicle.service;

import javax.annotation.Resource;

import org.btkj.pojo.model.EmployeeForm;
import org.btkj.vehicle.api.RenewlService;
import org.rapid.util.net.http.HttpProxy;
import org.springframework.stereotype.Service;

@Service("renewlService")
public class RenewlServiceImpl implements RenewlService {

	@Resource
	private HttpProxy httpProxy;
	
	@Override
	public void renewlInfo(EmployeeForm employeeForm, String license) {
		// TODO Auto-generated method stub
		
	}
}
