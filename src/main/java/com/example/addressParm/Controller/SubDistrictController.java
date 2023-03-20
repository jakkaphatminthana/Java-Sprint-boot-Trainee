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

import com.example.addressParm.Entity.SubDistrict;
import com.example.addressParm.Repository.SubDistrictRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("subdistrict")
public class SubDistrictController {

	@Autowired
	private SubDistrictRepository subdistrictRepository;

	// GET ALL
	@GetMapping("search")
	public ResponseEntity<?> searchSubDistrict() {
		List<SubDistrict> subdistricts = subdistrictRepository.findAll();
		return ResponseEntity.ok(subdistricts);
	}

	// Filter BY ID
	@GetMapping("/search/id")
	public ResponseEntity<?> searchSubDistrictId(@RequestParam Long id) {
		Optional<SubDistrict> subdistrict = subdistrictRepository.findById(id);
		if (subdistrict.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(subdistrict.get());
		}
	}

	// Filter BY Name
	@GetMapping("/search/name")
	public ResponseEntity<?> searchSubdistrictName(@RequestParam String name) {
		SubDistrict subdistrict = subdistrictRepository.findOneBySubdistrictName(name);

		if (subdistrict == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(subdistrict);
		}
	}

	// Filter BY Name contains
	@GetMapping("/search/name/contains")
	public ResponseEntity<?> searchSubdistrictNameContaine(@RequestParam String name) {
		List<SubDistrict> subdistricts = subdistrictRepository.findBySubdistrictNameContains(name);

		if (subdistricts.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(subdistricts);
		}
	}

	// Filter BY Name LIKE
	@GetMapping("/search/name/like")
	public ResponseEntity<?> searchSubdistrictNameLike(@RequestParam String name) {
		// เติม % เอาเอง
		List<SubDistrict> subdistricts = subdistrictRepository.findBySubdistrictNameLike(name + "%");

		if (subdistricts.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(subdistricts);
		}
	}

	// Filter BY ID and Active
	@GetMapping("/search/idactive")
	public ResponseEntity<?> searchSubDistrictIdActive(@RequestParam Long id, Boolean active) {
		List<SubDistrict> subdistricts = subdistrictRepository.findOneBySubdistrictIdAndActive(id, active);

		if (subdistricts == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(subdistricts);
		}
	}

	// Filter BY ID more than
	@GetMapping("/search/gt")
	public ResponseEntity<?> searchSubDistrictGT(@RequestParam(required = false) Long id) {
		List<SubDistrict> subdistricts = null;
		if (id == null) {
			subdistricts = subdistrictRepository.findAll();
		} else {
			subdistricts = subdistrictRepository.findBySubdistrictIdGreaterThanEqual(id);
		}

		return ResponseEntity.ok(subdistricts);
	}

	// Filter BY ID less than
	@GetMapping("/search/lt")
	public ResponseEntity<?> searchSubDistrictLT(@RequestParam(required = false) Long id) {
		List<SubDistrict> subdistricts = null;
		if (id == null) {
			subdistricts = subdistrictRepository.findAll();
		} else {
			subdistricts = subdistrictRepository.findBySubdistrictIdLessThanEqual(id);
		}

		return ResponseEntity.ok(subdistricts);
	}

	// GET Pageable
	@GetMapping("search/pageable")
	public ResponseEntity<?> searchPageable(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "subdistrictId") String sortBy,
			@RequestParam(defaultValue = "asc") String order) {

		Sort sort = null;
		if (order.equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<SubDistrict> subdistricts = subdistrictRepository.findAll(pageable);
		return ResponseEntity.ok(subdistricts);
	}

	// ------------------------------------------------------------------------------------------------
	// Create
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody SubDistrict subdistrict) {
		subdistrict = subdistrictRepository.save(subdistrict);
		return ResponseEntity.ok(subdistrict);
	}

	// -----------------------------------------------------------------------------------------------
	// DELETE
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		Optional<SubDistrict> subdistrict = subdistrictRepository.findById(id);
		if (subdistrict.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			subdistrictRepository.delete(subdistrict.get());
			return ResponseEntity.ok("ลบข้อมูลเรียบร้อย");
		}
	}

}
