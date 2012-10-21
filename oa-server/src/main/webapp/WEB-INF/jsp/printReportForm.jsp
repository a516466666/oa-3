<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>打印报审表</title>
<style>
body,div,span,th,td,input,button,a,select {
	padding: 0px;
	color: #333333;
	font-family: Calibri, Verdana, Arial;
	font-size: 16px;
}
p {
	margin: 0;
}
table {
      border:1px solid #000;
      border-width:1px 0 0 1px;
      margin:2px 0 2px 0;
      text-align:center;
      border-collapse:collapse;
}
td,th {
      padding:3px;
      font-size:12px;
      border:1px solid #000;
      border-width:0 1px 1px 0;
      margin:2px 0 2px 0;
      text-align:left;
}
th {
      text-align:center;
      font-weight:600;
      background-color:#F4F4F4;
}
.titlefont {
	font-size:24px;
}
.fangsong {
	font-family:FangSong_GB2312;
	font-size:15px;
}
</style>
</head>
<body>

<button id="printButton" type="button" onclick="window.print();">打印</button>
	
	<p align=center ><span class="titlefont">赣州市国土资源局章贡分局建设用地报审表</span></p>
	<p align=center style="margin:10px 0 10px 0"><span>报审事项：（${printedReportForm.matter}）</span>&nbsp;
	<span>编号：（${printedReportForm.formId}）</span>&nbsp;&nbsp;&nbsp; <span>日期：（${printedReportForm.sendTime}）</span></p>
	
	<div align=center>
	
	<table>
	 <tr>
	  <td width=54 valign=top >
	  <p><span>现土地使用者</span></p>
	  </td>
	  <td width=48 valign=top >
	  <p><span >原土地使用者</span></p>
	  </td>
	  <td width=60 valign=top>
	  <p><span>土地座落</span></p>
	  </td>
	  <td width=80 valign=top>
	  <p ><span >用地面积（㎡、亩）</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span >原用途</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span >现用途</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span >报审事项</span></p>
	  </td>
	  <td width=205 valign=top>
	  <p ><span >报审事项及说明</span></p>
	  </td>
	  <td width=119 colspan=2 valign=top>
	  <p ><b><span >办理依据</span></b></p>
	  </td>
	  <td width=109 valign=top>
	  <p ><b><span >报审单位意见</span></b></p>
	  </td>
	 </tr>
	 <tr>
	  <td width=54 valign=top >
	  <p ><span class="fangsong">${printedReportForm.landUser}</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span class="fangsong">${printedReportForm.originalLandUser}</span></p>
	  </td>
	  <td width=60 valign=top>
	  <p ><span class="fangsong">${printedReportForm.landLocation}</span></p>
	  </td>
	  <td width=80 valign=top>
	  <p ><span class="fangsong">${printedReportForm.landArea}</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span class="fangsong">${printedReportForm.originalLandUse}</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span class="fangsong">${printedReportForm.landUse}</span></p>
	  </td>
	  <td width=48 valign=top>
	  <p ><span class="fangsong">${printedReportForm.matter}</span></p>
	  </td>
	  <td width=205 valign=top>
	  <p ><span class="fangsong">${printedReportForm.matterDetail}</span></p>
	  </td>
	  <td width=119 colspan=2 valign=top>
	  <p ><span class="fangsong">${printedReportForm.policyBasis}</span></p>
	  </td>
	  <td width=109 valign=top>
	  <p ><span class="fangsong">${printedReportForm.comment}</span></p>
	  </td>
	 </tr>
	 <tr>
	  <td width=799 colspan=11 valign=top>
	  <p ><span >单位主要负责人：</span><span class="fangsong"
	     >${printedReportForm.responsiblePerson}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <span >审核人：</span><span  
	class="fangsong">${printedReportForm.auditor}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <span >制表人：</span><span  class="fangsong"
	   >${printedReportForm.tabulator}</span>
	   </p>
	  </td>
	 </tr>
	 <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >耕保科</span></p>
	  <p ><span >${orgunitFeedbackMap.get("OFFICE").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("OFFICE").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("OFFICE").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("OFFICE").feedBackTime}</span></p>
	  </td>
	 </tr>
	  <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >利用科</span></p>
	  <p ><span >${orgunitFeedbackMap.get("利用科").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("利用科").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("利用科").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("利用科").feedBackTime}</span></p>
	  </td>
	 </tr>
	  <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >地籍科</span></p>
	  <p ><span >${orgunitFeedbackMap.get("地籍科").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("地籍科").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("地籍科").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("地籍科").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >法规科</span></p>
	  <p ><span >${orgunitFeedbackMap.get("法规科").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("法规科").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("法规科").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("法规科").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >交易中心</span></p>
	  <p ><span >${orgunitFeedbackMap.get("交易中心").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("交易中心").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("交易中心").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("交易中心").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >收储中心</span></p>
	  <p ><span >${orgunitFeedbackMap.get("收储中心").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("收储中心").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("收储中心").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("收储中心").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >监察支队</span></p>
	  <p ><span >${orgunitFeedbackMap.get("监察支队").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("监察支队").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("监察支队").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("监察支队").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >市局分管领导</span></p>
	  <p ><span >${orgunitFeedbackMap.get("市局分管领导").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("市局分管领导").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("市局分管领导").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("市局分管领导").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >市局主要领导</span></p>
	  <p ><span >${orgunitFeedbackMap.get("市局主要领导").feedBackTime}</span></p>
	  </td>
	  <td width=516 colspan=7 valign=top>
	  <p ><span class="fangsong">${orgunitFeedbackMap.get("市局主要领导").content}</span></p>
	  </td>
	  <td width=181 colspan=2 valign=top>
	  <p ><span class="fangsong" >签名：${orgunitFeedbackMap.get("市局主要领导").signature}</span></p>
	  <p ><span class="fangsong">日期：${orgunitFeedbackMap.get("市局主要领导").feedBackTime}</span></p>
	  </td>
	 </tr>
	   <tr>
	  <td width=102 colspan=2 valign=top>
	  <p ><span >市局意见</span></p>
	  </td>
	  <td width=697 colspan=9 valign=top>
	  <p ><span class="fangsong">同意</span></p>
	  </td>
	 </tr>
	</table>
	
	</div>	
		
</body>
</html>