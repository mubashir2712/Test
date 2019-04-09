package com.example.ConnectorApp.controller

import java.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, ResponseBody, RestController}
import com.example.ConnectorApp.service.TableauConnectorService
import org.springframework.stereotype.Controller

@Controller
class mainContoller @Autowired() (private val tableauConnectorService: TableauConnectorService) {

    @GetMapping(value = Array("/TableuContainer"))
    def tableauContent(@RequestParam reqParam: util.Map[String, String], model: Model): String = {
        println("TableauContainer")
    model.addAttribute("userMappingBO", tableauConnectorService.genericRowMapper(reqParam))
    return "tableucontainer"
    }

    @GetMapping(value = Array("/TablauReportURL"), produces = Array("text/plain"))
    def tablauReportURL(@RequestParam reqParam: util.Map[String, String]): String = {
        println("inside url")
    return tableauConnectorService.pageContent(reqParam)
    }

    @GetMapping(value = Array("/NiceHome"))
    def tableauContent: String = {
        println("Inside NiceHome")
    return "nicehome"
    }

}
