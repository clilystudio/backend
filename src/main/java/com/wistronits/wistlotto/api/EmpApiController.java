package com.wistronits.wistlotto.api;

import java.util.List;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.wistlotto.model.CommonResultModel;
import com.wistronits.wistlotto.model.EmpInfoModel;
import com.wistronits.wistlotto.model.UploadCSVModel;
import com.wistronits.wistlotto.model.tables.TEmpInfo;
import com.wistronits.wistlotto.service.EmpService;

/**
 * 员工信息相关API
 * 
 * @author 盛广立 2019年1月11日
 */
@RestController
@RequestMapping(value = "/api/emp")
public class EmpApiController {

	@Inject
	private EmpService empService;

	/**
	 * 获取员工一览
	 * 
	 * @return 员工一览
	 */
	@GetMapping("/list")
	public List<EmpInfoModel> getList() {
		return empService.getList();
	}

	/**
	 * 添加员工信息
	 * 
	 * @param empInfo 员工信息
	 * @return 添加结果
	 */
	@PostMapping("/add")
	public CommonResultModel add(@RequestBody TEmpInfo empInfo) {
		return empService.add(empInfo);
	}

	/**
	 * 删除指定ID的员工
	 * 
	 * @param empIds 员工ID数组
	 * @return 删除结果
	 */
	@PostMapping("/delete")
	public CommonResultModel delete(@RequestBody String[] empIds) {
		return empService.delete(empIds);
	}

	/**
	 * 编辑员工信息
	 * 
	 * @param empInfo 员工信息
	 * @return 编辑结果
	 */
	@PostMapping("/edit")
	public CommonResultModel edit(@RequestBody TEmpInfo empInfo) {
		return empService.edit(empInfo);
	}

	/**
	 * 批量导入员工数据
	 * 
	 * @param model 员工数据信息
	 * @return 导入结果
	 */
	@PostMapping("/upload")
	public CommonResultModel uploadAll(@ModelAttribute UploadCSVModel model) {
		return empService.uploadAll(model.getFile(), model.isClearAll());
	}
}
