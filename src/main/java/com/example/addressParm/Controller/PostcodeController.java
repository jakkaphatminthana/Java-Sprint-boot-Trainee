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

import com.example.addressParm.Entity.Postcode;
import com.example.addressParm.Repository.PostcodeRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("postcode")
public class PostcodeController {

	@Autowired
	private PostcodeRepository postcodeRepository;

	// GET ALL
	@GetMapping("search")
	public ResponseEntity<?> searchPostcode() {
		List<Postcode> postcodes = postcodeRepository.findAll();
		return ResponseEntity.ok(postcodes);
	}

	// Filter BY ID
	@GetMapping("/search/id")
	public ResponseEntity<?> searchPostcodeId(@RequestParam Long id) {
		Optional<Postcode> postcode = postcodeRepository.findById(id);
		if (postcode.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(postcode.get());
		}
	}

	// Filter BY Name
	@GetMapping("/search/name")
	public ResponseEntity<?> searchPostcode(@RequestParam String name) {
		Postcode postcode = postcodeRepository.findOneByPostcode(name);

		if (postcode == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(postcode);
		}
	}

	// Filter BY Name contains
	@GetMapping("/search/name/contains")
	public ResponseEntity<?> searchPostcodeContaine(@RequestParam String name) {
		List<Postcode> postcodes = postcodeRepository.findByPostcodeContains(name);

		if (postcodes.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(postcodes);
		}
	}

	// Filter BY Name LIKE
	@GetMapping("/search/name/like")
	public ResponseEntity<?> searchPostcodeLike(@RequestParam String name) {
		// เติม % เอาเอง
		List<Postcode> postcodes = postcodeRepository.findByPostcodeLike(name + "%");

		if (postcodes.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(postcodes);
		}
	}

	// Filter BY ID and Active
	@GetMapping("/search/idactive")
	public ResponseEntity<?> searchPostcodeIdActive(@RequestParam Long id, Boolean active) {
		List<Postcode> postcodes = postcodeRepository.findOneByPostcodeIdAndActive(id, active);

		if (postcodes == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			return ResponseEntity.ok(postcodes);
		}
	}

	// Filter BY ID more than
	@GetMapping("/search/gt")
	public ResponseEntity<?> searchPostcodeGT(@RequestParam(required = false) Long id) {
		List<Postcode> postcodes = null;
		if (id == null) {
			postcodes = postcodeRepository.findAll();
		} else {
			postcodes = postcodeRepository.findByPostcodeIdGreaterThanEqual(id);
		}

		return ResponseEntity.ok(postcodes);
	}

	// Filter BY ID less than
	@GetMapping("/search/lt")
	public ResponseEntity<?> searchPostcodeLT(@RequestParam(required = false) Long id) {
		List<Postcode> postcodes = null;
		if (id == null) {
			postcodes = postcodeRepository.findAll();
		} else {
			postcodes = postcodeRepository.findByPostcodeIdLessThanEqual(id);
		}

		return ResponseEntity.ok(postcodes);
	}

	// GET Pageable
	@GetMapping("search/pageable")
	public ResponseEntity<?> searchPageable(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "postcodeId") String sortBy,
			@RequestParam(defaultValue = "asc") String order) {

		Sort sort = null;
		if (order.equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Postcode> postcodes = postcodeRepository.findAll(pageable);
		return ResponseEntity.ok(postcodes);
	}

	// ------------------------------------------------------------------------------------------------
	// Create
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Postcode postcode) {
		postcode = postcodeRepository.save(postcode);
		return ResponseEntity.ok(postcode);
	}

	// -----------------------------------------------------------------------------------------------
	// DELETE
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		Optional<Postcode> postcode = postcodeRepository.findById(id);
		if (postcode.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ไม่พบข้อมูล");
		} else {
			postcodeRepository.delete(postcode.get());
			return ResponseEntity.ok("ลบข้อมูลเรียบร้อย");
		}
	}
}
