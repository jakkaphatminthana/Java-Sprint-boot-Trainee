package com.example.addressParm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.addressParm.Entity.Postcode;

@Repository
public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

	// SELETE * From Postcode WHERE PostcodeName = ?
	Postcode findOneByPostcode(String postcode);

	// SELETE * From Postcode WHERE Postcode Like = '%?%'
	List<Postcode> findByPostcodeContains(String postcode);

	// SELETE * From Postcode WHERE Postcode Like = '?'
	List<Postcode> findByPostcodeLike(String postcode);

	// SELETE * From Postcode WHERE PostcodeId > ?
	List<Postcode> findByPostcodeIdGreaterThan(Long postcodeId);

	// SELETE * From Postcode WHERE PostcodeId >= ?
	List<Postcode> findByPostcodeIdGreaterThanEqual(Long postcodeId);

	// SELETE * From Postcode WHERE PostcodeId < ?
	List<Postcode> findByPostcodeIdLessThan(Long postcodeId);

	// SELETE * From Postcode WHERE PostcodeId <= ?
	List<Postcode> findByPostcodeIdLessThanEqual(Long postcodeId);

	// SELETE * From Postcode WHERE PostcodeId = ? AND active = true
	List<Postcode> findOneByPostcodeIdAndActive(Long postcodeId, Boolean active);
}
