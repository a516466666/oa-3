package org.huamuzhen.oa.biz;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.huamuzhen.oa.domain.dao.OrgUnitDAO;
import org.huamuzhen.oa.domain.entity.OrgUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrgUnitManager extends BaseManager<OrgUnit, String> {
	
	@Resource
	public void setDao(OrgUnitDAO dao) {
		super.setDao(dao);
	}
	
	@Transactional
	public void deleteOrgUnit(String id){
		this.delete(id);
	}
	
	@Transactional
	public Page<OrgUnit> findAllOrgUnit(Pageable page) {
		return this.findAll(page);
	}
	
	@Transactional
	public List<OrgUnit> findAllOrgUnit(){
		return this.findAll();
	}

	@Transactional
	public OrgUnit updateExisting(String id, String name, String description,
			String parentId) {
		OrgUnit orgUnit = this.findOne(id);
		orgUnit = setBasicDataForOrgUnit(orgUnit,name,description,parentId);
		return this.save(orgUnit);
		
	}

	@Transactional
	public OrgUnit createNew(String name, String description, String parentId) {
		OrgUnit newOrgUnit = new OrgUnit();
		newOrgUnit = setBasicDataForOrgUnit(newOrgUnit,name,description,parentId);
		newOrgUnit.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return this.save(newOrgUnit);
	}
	
	private OrgUnit setBasicDataForOrgUnit(final OrgUnit orgUnit,String name, String description, String parentId){
		orgUnit.setName(name);
		orgUnit.setDescription(description);
		orgUnit.setParentId(parentId);
		orgUnit.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		return orgUnit;
	}
}
