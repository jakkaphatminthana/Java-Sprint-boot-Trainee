package com.example.addressParm.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.addressParm.Entity.Province;
import com.example.addressParm.Repository.ProvinceRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("province")
public class ProvinceController {

	@Autowired
	private ProvinceRepository provinceRepository;

	// GET ALL
	@GetMapping("search")
	public ResponseEntity<?> searchProvince() {
		List<Province> provinces = provinceRepository.findAll();
		return ResponseEntity.ok(provinces);
	}

	// Filter BY ID
	@GetMapping("/search/id")
	public ResponseEntity<?> searchProvinceId(@RequestParam Long id) {
		Optional<Province> province = provinceRepository.findById(id);
		if (province.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(province.get());
		}
	}

	// Filter BY Name
	@GetMapping("/search/name")
	public ResponseEntity<?> searchProvinceName(@RequestParam String name) {
		Province province = provinceRepository.findOneByProvinceName(name);

		if (province == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(province);
		}
	}

	// Filter BY Name contains
	@GetMapping("/search/name/contains")
	public ResponseEntity<?> searchProvinceNameContaine(@RequestParam String name) {
		List<Province> provinces = provinceRepository.findByProvinceNameContains(name);

		if (provinces.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(provinces);
		}
	}

	// Filter BY Name LIKE
	@GetMapping("/search/name/like")
	public ResponseEntity<?> searchProvinceNameLike(@RequestParam String name) {
		// เติม % เอาเอง
		List<Province> provinces = provinceRepository.findByProvinceNameLike(name + "%");

		if (provinces.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(provinces);
		}
	}

	// Filter BY id and active
	@GetMapping("/search/idactive")
	public ResponseEntity<?> searchProvinceIdActive(@RequestParam Long id, Boolean active) {
		List<Province> provinces = provinceRepository.findOneByProvinceIdAndActive(id, active);

		if (provinces == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(provinces);
		}
	}

	// Filter BY ID more than
	@GetMapping("/search/gt")
	public ResponseEntity<?> searchProvinceGT(@RequestParam(required = false) Long id) {
		List<Province> provinces = null;
		if (id == null) {
			provinces = provinceRepository.findAll();
		} else {
			provinces = provinceRepository.findByProvinceIdGreaterThanEqual(id);
		}

		return ResponseEntity.ok(provinces);
	}

	// Filter BY ID less than
	@GetMapping("/search/lt")
	public ResponseEntity<?> searchProvinceLT(@RequestParam(required = false) Long id) {
		List<Province> provinces = null;
		if (id == null) {
			provinces = provinceRepository.findAll();
		} else {
			provinces = provinceRepository.findByProvinceIdLessThanEqual(id);
		}

		return ResponseEntity.ok(provinces);
	}

	// GET Pageable
	@GetMapping("search/pageable")
	public ResponseEntity<?> searchPageable(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "provinceId") String sortBy,
			@RequestParam(defaultValue = "asc") String order,
			@RequestParam(required = false) Long provinceFrom,
			@RequestParam(required = false) Long provinceTo
			) {

		Sort sort = null;
		if (order.equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Province> provinces = provinceRepository.findByCriteria(provinceFrom, provinceTo, pageable);
		return ResponseEntity.ok(provinces);
	}

	// ------------------------------------------------------------------------------------------------
	// Create
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Province province) {
		province = provinceRepository.save(province);
		return ResponseEntity.ok(province);
	}

	// -----------------------------------------------------------------------------------------------
	// DELETE
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		Optional<Province> province = provinceRepository.findById(id);
		if (province.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			provinceRepository.delete(province.get());
			return ResponseEntity.ok("ลบข้อมูลเรียบร้อย");
		}
	}
}
