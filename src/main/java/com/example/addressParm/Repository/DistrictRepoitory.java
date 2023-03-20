package com.example.addressParm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.addressParm.Entity.District;

@Repository
public interface DistrictRepoitory extends JpaRepository<District, Long>{
	
		// SELETE * From District WHERE DistrictName = ?
		District findOneByDistrictName(String districtName);

		// SELETE * From District WHERE DistrictName Like = '%?%'
		List<District> findByDistrictNameContains(String districtName);

		// SELETE * From District WHERE DistrictName Like = '?'
		List<District> findByDistrictNameLike(String districtName);

		// SELETE * From District WHERE DistrictId > ?
		List<District> findByDistrictIdGreaterThan(Long districtId);

		// SELETE * From District WHERE DistrictId >= ?
		List<District> findByDistrictIdGreaterThanEqual(Long districtId);

		// SELETE * From District WHERE DistrictId < ?
		List<District> findByDistrictIdLessThan(Long districtId);

		// SELETE * From District WHERE DistrictId <= ?
		List<District> findByDistrictIdLessThanEqual(Long districtId);

		// SELETE * From District WHERE DistrictId = ? AND active = true
		List<District> findOneByDistrictIdAndActive(Long districtId, Boolean active);
}
