package com.qgwy.template.bean;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;


@ToString
@Accessors(chain=true)
@Table(name="sys_log")
@Data
@Entity
public class SysLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String clazName;
  private String methodName;
  private String logLevel;
  private String logger;
  private String operation;
  private String message;
  private String params;
  private String userName;
  private Timestamp logDate;







}
