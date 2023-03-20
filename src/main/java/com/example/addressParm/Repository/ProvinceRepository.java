package com.example.addressParm.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.addressParm.Entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
	// Retrun type??
	// 1.Etity
	// 2.List<Entity>
	// 3.Pageable

	// SELETE * From province WHERE provinceName = ?
	Province findOneByProvinceName(String provinceName);

	// SELETE * From province WHERE provinceName Like = '%?%'
	List<Province> findByProvinceNameContains(String provinceName);

	// SELETE * From province WHERE provinceName Like = '?'
	List<Province> findByProvinceNameLike(String provinceName);

	// SELETE * From province WHERE provinceId > ?
	List<Province> findByProvinceIdGreaterThan(Long provinceId);

	// SELETE * From province WHERE provinceId >= ?
	List<Province> findByProvinceIdGreaterThanEqual(Long provinceId);

	// SELETE * From province WHERE provinceId < ?
	List<Province> findByProvinceIdLessThan(Long provinceId);

	// SELETE * From province WHERE provinceId <= ?
	List<Province> findByProvinceIdLessThanEqual(Long provinceId);

	// SELETE * From province WHERE provinceId = ? AND active = true
	List<Province> findOneByProvinceIdAndActive(Long provinceId, Boolean active);

	// ------------------------------------------------------------------------------------------------
	// TODO : Native query
//	@Query(value = "SELECT * FROM Province", nativeQuery = true)
//	List<Province> findProvinceAll();
//
//	@Query(value = "SELECT * FROM Province WHERE province_id = :id", nativeQuery = true)
//	List<Province> findProvinceAllWhereId(Long id);
//
//	@Query(value = "SELECT p.* " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN district d " 
//			+ "ON p.province_id = d.province_id "
//			+ "WHERE province_id = :id ", nativeQuery = true)
//	List<Province> findProvinceAllJOIN(Long id);
//
//	@Query(value = "SELECT p.* " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN district d " 
//			+ "ON p.province_id = d.province_id "
//			+ "LEFT JOIN Sub_district sb " 
//			+ "ON p.district_id = sb.district_id "
//			+ "WHERE province_id = :id ", nativeQuery = true)
//	List<Province> findProvinceAllJoin2(Long id);
//
//	@Query(value = "SELECT p.* " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN district d " 
//			+ "ON p.province_id = d.province_id "
//			+ "LEFT JOIN Sub_district sb " 
//			+ "ON p.district_id = sb.district_id " 
//			+ "LEFT JOIN Postcode po "
//			+ "ON sb.Sub_district_id = po.Sub_district_id " 
//			+ "WHERE province_id = :provinceId "
//			+ "AND postcode = :postcode ", nativeQuery = true)
//	List<Province> findProvinceAllJoin3(Long provinceId, String postcode);

	// ------------------------------------------------------------------------------------------------
	// TODO : JPQL
//	@Query(value = "SELECT p FROM Province p ")
//	List<Province> findProvinceAllJpql();
//
//	@Query(value = "SELECT p FROM Province p WHERE p.provinceId = :id")
//	List<Province> findProvinceAllJpqlWhereId();
//
//	@Query(value = "SELECT p " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN p.districts d " 
//			+ "WHERE p.provinceId = :id ")
//	List<Province> findProvinceAllJpqlJOIN(Long id);
//
//	@Query(value = "SELECT p " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN p.districts d " 
//			+ "LEFT JOIN d.subdistrict sd "
//			+ "WHERE p.provinceId = :id ")
//	List<Province> findProvinceAllJpqlJoin2(Long id);
//
//	@Query(value = "SELECT p " 
//			+ "FROM Province p " 
//			+ "LEFT JOIN p.districts d " 
//			+ "LEFT JOIN d.subdistrict sd "
//			+ "LEFT JOIN sd.postcode po " 
//			+ "WHERE p.provinceId = :provinceId " 
//			+ "AND po.postcode  = :postcode ")
//	List<Province> findProvinceAllJpqlJoin3(Long provinceId, String postcode);

	// JPQL Pageable
	@Query(value = "SELECT p FROM Province p "
			+ "WHERE (:provinceIdFrom IS NULL OR p.provinceId >= :provinceIdFrom) "
			+ "AND (:provinceIdTo IS NULL OR p.provinceId <= :provinceIdTo) ")
	Page<Province> findByCriteria(Long provinceIdFrom, Long provinceIdTo, Pageable pageable);

	Page<Province> findByProvinceIdGreaterThanEqualAndProvinceIdLessThanEqual(
			Long provinceIdFrom, 
			Long provinceIdTo,
			Pageable pageable);

}
