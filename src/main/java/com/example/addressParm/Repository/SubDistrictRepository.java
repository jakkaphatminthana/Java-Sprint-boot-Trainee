package com.example.addressParm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.addressParm.Entity.SubDistrict;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {

	// SELETE * From Sub_District WHERE SubDistrictName = ?
	SubDistrict findOneBySubdistrictName(String subdistrictName);

	// SELETE * From Subdistrict WHERE SubdistrictName Like = '%?%'
	List<SubDistrict> findBySubdistrictNameContains(String subdistrictName);

	// SELETE * From Subdistrict WHERE SubdistrictName Like = '?'
	List<SubDistrict> findBySubdistrictNameLike(String subdistrictName);

	// SELETE * From Subdistrict WHERE SubdistrictId > ?
	List<SubDistrict> findBySubdistrictIdGreaterThan(Long subdistrictId);

	// SELETE * From Subdistrict WHERE SubdistrictId >= ?
	List<SubDistrict> findBySubdistrictIdGreaterThanEqual(Long subdistrictId);

	// SELETE * From Subdistrict WHERE SubdistrictId < ?
	List<SubDistrict> findBySubdistrictIdLessThan(Long subdistrictId);

	// SELETE * From Subdistrict WHERE SubdistrictId <= ?
	List<SubDistrict> findBySubdistrictIdLessThanEqual(Long subdistrictId);

	// SELETE * From Subdistrict WHERE SubdistrictId = ? AND active = true
	List<SubDistrict> findOneBySubdistrictIdAndActive(Long subdistrictId, Boolean active);
}
