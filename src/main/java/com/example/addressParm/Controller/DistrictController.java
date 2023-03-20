package com.example.addressParm.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.example.addressParm.Entity.District;
import com.example.addressParm.Repository.DistrictRepoitory;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("district")
public class DistrictController {

	@Autowired
	private DistrictRepoitory districtRepository;

	// GET ALL
	@GetMapping("search")
	public ResponseEntity<?> searchDistrict() {
		List<District> districts = districtRepository.findAll();
		return ResponseEntity.ok(districts);
	}

	// Filter BY ID
	@GetMapping("/search/id")
	public ResponseEntity<?> searchDistrictId(@RequestParam Long id) {
		Optional<District> district = districtRepository.findById(id);
		if (district.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(district.get());
		}
	}

	// Filter BY Name
	@GetMapping("/search/name")
	public ResponseEntity<?> searchDistrictName(@RequestParam String name) {
		District district = districtRepository.findOneByDistrictName(name);

		if (district == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(district);
		}
	}

	// Filter BY Name contains
	@GetMapping("/search/name/contains")
	public ResponseEntity<?> searchDistrictNameContaine(@RequestParam String name) {
		List<District> districts = districtRepository.findByDistrictNameContains(name);

		if (districts.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(districts);
		}
	}

	// Filter BY Name LIKE
	@GetMapping("/search/name/like")
	public ResponseEntity<?> searchDistrictNameLike(@RequestParam String name) {
		// เติม % เอาเอง
		List<District> districts = districtRepository.findByDistrictNameLike(name + "%");

		if (districts.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(districts);
		}
	}

	// Filter BY Id and Active
	@GetMapping("/search/idactive")
	public ResponseEntity<?> searchDistrictIdActive(@RequestParam Long id, Boolean active) {
		List<District> districts = districtRepository.findOneByDistrictIdAndActive(id, active);

		if (districts == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(districts);
		}
	}

	// Filter BY ID more than
	@GetMapping("/search/gt")
	public ResponseEntity<?> searchDistrictGT(@RequestParam(required = false) Long id) {
		List<District> districts = null;
		if (id == null) {
			districts = districtRepository.findAll();
		} else {
			districts = districtRepository.findByDistrictIdGreaterThanEqual(id);
		}

		return ResponseEntity.ok(districts);
	}

	// Filter BY ID less than
	@GetMapping("/search/lt")
	public ResponseEntity<?> searchDistrictLT(@RequestParam(required = false) Long id) {
		List<District> districts = null;
		if (id == null) {
			districts = districtRepository.findAll();
		} else {
			districts = districtRepository.findByDistrictIdLessThanEqual(id);
		}

		return ResponseEntity.ok(districts);
	}

	// GET Pageable
	@GetMapping("search/pageable")
	public ResponseEntity<?> searchPageable(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "districtId") String sortBy,
			@RequestParam(defaultValue = "asc") String order) {

		Sort sort = null;
		if (order.equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<District> districts = districtRepository.findAll(pageable);
		return ResponseEntity.ok(districts);
	}

	// ------------------------------------------------------------------------------------------------
	// Create
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody District district) {
		district = districtRepository.save(district);
		return ResponseEntity.ok(district);
	}

	// -----------------------------------------------------------------------------------------------
	// DELETE
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		Optional<District> district = districtRepository.findById(id);
		if (district.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			districtRepository.delete(district.get());
			return ResponseEntity.ok("ลบข้อมูลเรียบร้อย");
		}
	}

}
