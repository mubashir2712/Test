package com.example.ConnectorApp.service

import java.util
import com.example.ConnectorApp.beans.UserBean
import org.springframework.http._
import org.springframework.stereotype.Component
import org.springframework.util.{LinkedMultiValueMap, MultiValueMap}
import org.springframework.web.client.RestTemplate

@Component
class TableauConnectorService {

  //private val tableauServerPath = "http://10.128.163.57:6060/"
  private val tableauServerPath: String = "http://10.220.160.51:6060/"

  private val r1 = "MSE_CCT_WB/LandingPage"
  private val r2 = "Fraud_DQ_WB/SummaryDashboard"


  def pageContent(reqParam: util.Map[String, String]): String = {
    var authToken = ""
    var tbs = ""
    val userbean = genericRowMapper(reqParam)
    authToken = this.getTicketID(userbean)
    tbs = calltableauService(userbean, authToken)

    return tbs
  }

  def getTicketID(userbean: UserBean): String = {

    val restTemplate = new RestTemplate
    val headers = new HttpHeaders
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
    val map = new LinkedMultiValueMap[String, String]
    map.add("username", userbean.getUserId)
    map.add("target_site", userbean.getTenantId)
    var entity = new HttpEntity[MultiValueMap[String, String]](map, headers)
    var uri = tableauServerPath + "trusted"
    var response = restTemplate.exchange(uri, HttpMethod.POST, entity, classOf[String])
    response.getBody
  }

  def calltableauService(userbean: UserBean, authToken: String): String = {
    val tableauURL = new StringBuilder
    tableauURL.append(tableauServerPath)
    tableauURL.append("trusted/")
    tableauURL.append(authToken)
    tableauURL.append("/t/")
    tableauURL.append(userbean.getReportId)
    tableauURL.append("/views/")
    if ("r1" == userbean.getTenantId) tableauURL.append(r1)
    if ("r2" == userbean.getTenantId) tableauURL.append(r2)
    tableauURL.append("?iframeSizedToWindow=true&:embed=y&:showAppBanner=false&:display_count=no&:showVizHome=no")
    return tableauURL.toString
  }

  def genericRowMapper(reqParam: util.Map[String, String]): UserBean = {
    val userbean = new UserBean(reqParam.get("uname"), reqParam.get("sitename"), reqParam.get("report"))
    return userbean
  }
}
