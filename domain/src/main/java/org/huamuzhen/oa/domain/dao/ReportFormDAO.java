package org.huamuzhen.oa.domain.dao;

import java.util.List;

import org.huamuzhen.oa.domain.entity.ReportForm;
import org.huamuzhen.oa.domain.enumeration.ReportFormStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportFormDAO extends JpaDAO<ReportForm, String> {
	
	//Need to be optimized
	@Transactional
	@Query(value="SELECT r.formId FROM ReportForm r WHERE r.formId LIKE :fuzzyQueryCondition ORDER BY r.createdAt DESC")
	public List<String> findNewCreatedFormIdListOfToday(@Param("fuzzyQueryCondition") String fuzzyQueryCondition);
	
	@Transactional
	public List<ReportForm> findReportFormByStatusAndCurrentReceiverId(ReportFormStatus status, String currentReceiverId);
	
	@Transactional
	public List<ReportForm> findReportFormByStatus(ReportFormStatus status);
	
	@Transactional
	@Query(value="FROM ReportForm r WHERE (r.formId LIKE :formId) AND (r.landUser LIKE :landUser) AND (r.landLocation LIKE :landLocation)")
	public List<ReportForm> queryReportFromByKeyword(@Param("formId") String formId, @Param("landUser") String landUser, @Param("landLocation") String landLocation);
	
}
