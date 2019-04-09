package com.example.ConnectorApp.beans

import scala.beans.BeanProperty

class UserBean(@BeanProperty val userId : String, @BeanProperty val tenantId : String,
               @BeanProperty val reportId : String) {
  def this() = this(null,null,null)

  override def toString: String = {
    return this.userId + " " + this.tenantId + " " + this.reportId
  }
}
