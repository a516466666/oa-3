package org.huamuzhen.oa.server.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.huamuzhen.oa.biz.FeedbackManager;
import org.huamuzhen.oa.biz.ReportFormManager;
import org.huamuzhen.oa.biz.ReportFormTitleManager;
import org.huamuzhen.oa.biz.ReportFormTypeManager;
import org.huamuzhen.oa.biz.UserManager;
import org.huamuzhen.oa.domain.entity.Feedback;
import org.huamuzhen.oa.domain.entity.OrgUnit;
import org.huamuzhen.oa.domain.entity.ReportForm;
import org.huamuzhen.oa.domain.entity.ReportFormTitle;
import org.huamuzhen.oa.domain.entity.ReportFormType;
import org.huamuzhen.oa.domain.entity.User;
import org.huamuzhen.oa.domain.enumeration.Privilege;
import org.huamuzhen.oa.domain.enumeration.ReportFormStatus;
import org.huamuzhen.oa.server.util.FeedbackComparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reportForm")
public class ReportFormController {
	
	@Resource
	private ReportFormManager reportFormManager;
	
	@Resource
	private ReportFormTypeManager reportFormTypeManager;
	
	@Resource
	private FeedbackManager feedbackManager;
	
	@Resource
	private UserManager userManager;
	
	@Resource
	private ReportFormTitleManager reportFormTitleManager;
	
	@RequestMapping(value = { "", "/" })
	public String index(HttpServletRequest request){
		
		return "reportForm";
	}
	
	@RequestMapping("/addReportForm")
	public ModelAndView addReportForm(){
		ModelAndView mav = new ModelAndView("addReportForm");
		List<ReportFormType> reportFormTypeList = reportFormTypeManager.findAll();
		mav.addObject("reportFormTypeList", reportFormTypeList);
		
		List<ReportFormTitle> reportFormTitleList = reportFormTitleManager.findAll();
		mav.addObject("reportFormTitleList", reportFormTitleList);
		
		return mav;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request){
		String reportFormTypeId = request.getParameter("reportFormTypeId");
		
		String title = request.getParameter("title");
		if(title.trim().equals("")){
			title = null;
		}
		
		String landUser = request.getParameter("landUser");
		String originalLandUser = request.getParameter("originalLandUser");
		String landLocation = request.getParameter("landLocation");
		String landArea = request.getParameter("landArea");
		String landAreaMeasure = request.getParameter("landAreaMeasure");
		String landUse = request.getParameter("landUse");
		String originalLandUse = request.getParameter("originalLandUse");
		String matter = request.getParameter("matter");
		String matterDetail = request.getParameter("matterDetail");
		String policyBasis = request.getParameter("policyBasis");
		String comment = request.getParameter("comment");
		String responsiblePerson = request.getParameter("responsiblePerson");
		String auditor = request.getParameter("auditor");
		String tabulator = request.getParameter("tabulator");
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		
	    ReportForm newReportForm = reportFormManager.saveReportForm(null, reportFormTypeId, title, null,
				landUser, originalLandUser, landLocation, new BigDecimal(landArea),landAreaMeasure, landUse,
				originalLandUse, matter, matterDetail, policyBasis, comment,
				responsiblePerson,auditor,tabulator, currentUser.getId());
	    
		ModelAndView mav = new ModelAndView("viewReportForm");
		mav.addObject("reportForm", newReportForm);
		return mav;
	}
	
	@RequestMapping(value="/editUnsendReportForm/{id}",method=RequestMethod.POST)
	public ModelAndView editUnsendReportForm(@PathVariable String id){
		ReportForm selectedReportForm =reportFormManager.findOne(id);
		ModelAndView mav = new ModelAndView("editReportForm");
		mav.addObject("selectedReportForm", selectedReportForm);
		List<ReportFormType> reportFormTypeList = reportFormTypeManager.findAll();
		mav.addObject("reportFormTypeList", reportFormTypeList);
		
		List<ReportFormTitle> reportFormTitleList = reportFormTitleManager.findAll();
		mav.addObject("reportFormTitleList", reportFormTitleList);
		
		return mav;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		String reportFormTypeId = request.getParameter("reportFormTypeId");
		String title = request.getParameter("title");
		String formId = request.getParameter("formId");
		String landUser = request.getParameter("landUser");
		String originalLandUser = request.getParameter("originalLandUser");
		String landLocation = request.getParameter("landLocation");
		String landArea = request.getParameter("landArea");
		String landAreaMeasure = request.getParameter("landAreaMeasure");
		String landUse = request.getParameter("landUse");
		String originalLandUse = request.getParameter("originalLandUse");
		String matter = request.getParameter("matter");
		String matterDetail = request.getParameter("matterDetail");
		String policyBasis = request.getParameter("policyBasis");
		String comment = request.getParameter("comment");
		String responsiblePerson = request.getParameter("responsiblePerson");
		String auditor = request.getParameter("auditor");
		String tabulator = request.getParameter("tabulator");
		reportFormManager.saveReportForm(id, reportFormTypeId, title, formId,
				landUser, originalLandUser, landLocation, new BigDecimal(landArea),landAreaMeasure, landUse,
				originalLandUse, matter, matterDetail, policyBasis, comment,
				responsiblePerson,auditor,tabulator, null);
		
		
		return "redirect:/reportForm/list/notSendReportForm";
	}
	
	@RequestMapping(value="/sendToOrgUnits/{id}", method=RequestMethod.POST)
	public String sendToOrgUnits(@PathVariable String id){
		reportFormManager.sendToOrgUnits(id);
		return "redirect:/reportForm/list/notSendReportForm";
		
	}
	
	@RequestMapping(value="/sendToLeader1/{id}", method=RequestMethod.POST)
	public String sendToLeader1(@PathVariable String id, HttpServletRequest request){
		String leader1Id = request.getParameter("leader1Id");
		if(leader1Id.equals("")){
			leader1Id = null;
		}
		reportFormManager.sendToLeader1(id, leader1Id);	
		return "redirect:/reportForm/list/gotReplyFromUnitsReportForm";
	}
	
	@RequestMapping(value="/list/{reportFormStatusLink}")
	public ModelAndView list(@PathVariable String reportFormStatusLink, HttpSession session){
		ModelAndView mav = new ModelAndView("listResponseReportForm");
		User currentUser = (User)session.getAttribute("currentUser");
		List<ReportForm> reportFormList = null;
		if (reportFormStatusLink.equals("sentToLeader1ReportForm")
				|| reportFormStatusLink.equals("sentToLeader2ReportForm")) {
			reportFormList = reportFormManager.findReportFormByStatusAndCurrentReceiverId(reportFormStatusLink,currentUser.getId());
			List<ReportForm> reportFormListWhichCurrentReceiverIdIsNull = reportFormManager.findReportFormByStatusAndCurrentReceiverId(reportFormStatusLink,null);
			reportFormList.addAll(reportFormListWhichCurrentReceiverIdIsNull);
		}else if(reportFormStatusLink.equals("sentToOrgUnitsReportForm")){
			// need to be optimized, use one HQL instead of looping
			List<ReportForm> allReportFormList = reportFormManager.findReportFormByStatus(reportFormStatusLink);
			reportFormList = new ArrayList<ReportForm>();
			for(ReportForm reportForm : allReportFormList){
				// check if the reportForm is already answered by current org_unit
				List<Feedback> feedbackList = feedbackManager.findFeedbackByResponseOrgUnitIdAndReportFormId(currentUser.getOrgUnit().getId(), reportForm.getId());
				if(feedbackList.size() == 0){
					reportFormList.add(reportForm);
				}
			}
		}else{ // all normal user share all report  
			reportFormList = reportFormManager.findReportFormByStatus(reportFormStatusLink);
		}
		// check if there are reportForms which is urgent
		if(reportFormStatusLink.equals("sentToLeader1ReportForm")
				|| reportFormStatusLink.equals("sentToLeader2ReportForm") || reportFormStatusLink.equals("sentToOrgUnitsReportForm")
				|| reportFormStatusLink.equals("gotReplyFromUnitsReportForm") || reportFormStatusLink.equals("sentToOfficeReportForm")){
			for(ReportForm reportForm : reportFormList){
				if(null != reportForm.getSendTime()){
					long sendTime = reportForm.getSendTime().getTime();
					long now = System.currentTimeMillis();
					if (now - sendTime > 86400000 * 2){
						mav.addObject("warningMsg", "twoDaysDelay");
						break;
					}else if (now - sendTime > 86400000){
						mav.addObject("warningMsg", "oneDayDelay");
					}
				}
			}
		}
		
		mav.addObject("reportFormList", reportFormList);
		mav.addObject("reportFormStatusLink", reportFormStatusLink);
		if(reportFormStatusLink.equals("gotReplyFromUnitsReportForm")){
			mav.addObject("leader1List", userManager.findUserByPrivilege(Privilege.LEADER1));
		}
		
		return mav;
	}
	
	@RequestMapping(value="responseReportForm/{id}",method=RequestMethod.POST)
	public ModelAndView responseReportForm(@PathVariable String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("responseReportForm");
		ReportForm selectedReportForm = reportFormManager.findOne(id);
		mav.addObject("selectedReportForm", selectedReportForm);
		if(selectedReportForm.getStatus() == ReportFormStatus.SENT_TO_ORG_UNITS){
			ReportFormType reportFormType = reportFormTypeManager.findOne(selectedReportForm.getReportFormType().getId());
			Set<OrgUnit> requiredOrgUnits = reportFormType.getRequiredOrgUnits();
			User user = (User)request.getSession().getAttribute("currentUser");
			OrgUnit qualifiedOrgUnit = null;
			for(OrgUnit orgUnit : requiredOrgUnits){
				if(user.getOrgUnit().getName().equals(orgUnit.getName())){
					 if(!feedbackManager.checkIfOrgUnitFeedbackIsAlreadyExists(orgUnit, id)){
						 qualifiedOrgUnit = orgUnit;
					 }else{
						 ModelAndView errorMav = new ModelAndView("error");
						 errorMav.addObject("errorMessage", "该部门已经回复");
						 return errorMav;
					 }
				}
			}
			mav.addObject("qualifiedOrgUnit", qualifiedOrgUnit);
		}else if(selectedReportForm.getStatus() == ReportFormStatus.SENT_TO_LEADER1){
			mav.addObject("leader2List", userManager.findUserByPrivilege(Privilege.LEADER2));
		}
		mav.addObject("responseType", selectedReportForm.getStatus());

		return mav;
	}	
	
	@RequestMapping(value="/reCreateReportForm/{id}",method=RequestMethod.POST)
	public ModelAndView reCreateReportForm(@PathVariable String id){
		ReportForm selectedReportForm =reportFormManager.findOne(id);
		ModelAndView mav = new ModelAndView("reCreateReportForm");
		mav.addObject("selectedReportForm", selectedReportForm);
		List<ReportFormType> reportFormTypeList = reportFormTypeManager.findAll();
		mav.addObject("reportFormTypeList", reportFormTypeList);
		
		List<ReportFormTitle> reportFormTitleList = reportFormTitleManager.findAll();
		mav.addObject("reportFormTitleList", reportFormTitleList);
		
		return mav;
	}
	
	@RequestMapping(value="/reCreate",method=RequestMethod.POST)
	public ModelAndView reCreate(HttpServletRequest request){
		
		String oldId = request.getParameter("oldId");
		String reportFormTypeId = request.getParameter("reportFormTypeId");
		String title = request.getParameter("title");
		String oldFormId = request.getParameter("oldFormId");
		String landUser = request.getParameter("landUser");
		String originalLandUser = request.getParameter("originalLandUser");
		String landLocation = request.getParameter("landLocation");
		String landArea = request.getParameter("landArea");
		String landAreaMeasure = request.getParameter("landAreaMeasure");
		String landUse = request.getParameter("landUse");
		String originalLandUse = request.getParameter("originalLandUse");
		String matter = request.getParameter("matter");
		String matterDetail = request.getParameter("matterDetail");
		String policyBasis = request.getParameter("policyBasis");
		String comment = request.getParameter("comment");
		String responsiblePerson = request.getParameter("responsiblePerson");
		String auditor = request.getParameter("auditor");
		String tabulator = request.getParameter("tabulator");
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		
		ReportForm newReportForm = reportFormManager.reCreateReportForm(oldId, reportFormTypeId, title, oldFormId,
				landUser, originalLandUser, landLocation, new BigDecimal(landArea),landAreaMeasure, landUse,
				originalLandUse, matter, matterDetail, policyBasis, comment,
				responsiblePerson,auditor,tabulator,currentUser.getId() );
		
		ModelAndView mav = new ModelAndView("viewReportForm");
		mav.addObject("reportForm", newReportForm);
		return mav;

	}
	
	@RequestMapping(value="/printReportForm/{id}",method=RequestMethod.POST)
	public ModelAndView printReportForm(@PathVariable String id){
		ReportForm printedReportForm =reportFormManager.findOne(id);
		ModelAndView mav = new ModelAndView("printReportForm");
		mav.addObject("printedReportForm", printedReportForm);
		
		List<Feedback> feedbackList = feedbackManager.findFeedbackByReportFormId(id);
		List<Feedback> feedbackFromOrgUnits = new ArrayList<Feedback>();
		// empty feedback
		Feedback feedbackFromLeader1 = new Feedback();
		Feedback feedbackFromLeader2 = new Feedback();
		Feedback feedbackFromOffice = new Feedback();
		for(Feedback feedback : feedbackList){
			if(feedback.getOwner().equals(Privilege.LEADER1.toString())){
				feedbackFromLeader1 = feedback;
			}else if(feedback.getOwner().equals(Privilege.LEADER2.toString())){
				feedbackFromLeader2 = feedback;
			}else if(feedback.getOwner().equals(Privilege.OFFICE.toString())){
				feedbackFromOffice = feedback;
			}else{
				feedbackFromOrgUnits.add(feedback);
			}
		}
		Collections.sort(feedbackFromOrgUnits,new FeedbackComparator());

		mav.addObject("feedbackFromOrgUnits", feedbackFromOrgUnits);
		mav.addObject("feedbackFromLeader1", feedbackFromLeader1);
		mav.addObject("feedbackFromLeader2", feedbackFromLeader2);
		mav.addObject("feedbackFromOffice", feedbackFromOffice);
		
		return mav;
	}
	
	@RequestMapping(value="/view/{id}",method=RequestMethod.POST)
	public ModelAndView view(@PathVariable String id){
		ModelAndView mav = new ModelAndView("viewReportForm");
		ReportForm selectedReportForm = reportFormManager.findOne(id);
		mav.addObject("reportForm", selectedReportForm);
		return mav;
	}

}
