package com.example.ConnectorApp;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(Array("com.example.ConnectorApp"))
@SpringBootApplication
class ConnectorAppApplication

object Application extends App {
	SpringApplication.run(classOf[ConnectorAppApplication])
}

