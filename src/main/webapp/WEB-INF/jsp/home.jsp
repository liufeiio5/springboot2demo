<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en" >
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/css/home.css">
</head>
<body>
  <canvas id="canvas" width="1280" height="1024">您的浏览器不支持canvas标签，请您更换浏览器 </canvas>
  <div class='selector'>
    <ul>
      <li>
        <a  href="/table">
          <input id='1' type='checkbox'>
          <label title="日报"><img src="/images/day.png"/></label>
        </a>
      </li>
      <li>
        <a  href="/weekly">
          <input id='2' type='checkbox'>
          <label title="周报"><img src="/images/week.png"/></label>
        </a>
      </li>
      <li>
        <a  href="/monthly">
          <input id='3' type='checkbox'>
          <label title="月报"><img src="/images/month.png"/></label>
        </a>
      </li>
      <li>
        <a  href="/dutyRecord">
          <input id='4' type='checkbox'>
          <label title="值班记录"><img src="/images/zb.png" /></label>
        </a>
      </li>
      <li>
        <a  href="/leaveRecord">
          <input id='5' type='checkbox'>
          <label title="请假记录"><img src="/images/qj.png"/></label>
        </a>
      </li>
      <li>
        <a  href="/overtime">
          <input id='6' type='checkbox'>
          <label title="加班记录"><img src="/images/overtime.png"/></label>
        </a>
      </li>
      <li>
        <a  href="http://192.168.1.62:8090" target="_blank">
          <input id='7' type='checkbox'>
          <label title="Confluence"><img src="/images/confluence.png"	/> </label>
        </a>
      </li>
      <li>
        <a  href="http://192.168.1.73:8080" target="_blank">
          <input id='8' type='checkbox'>
          <label title="Jira"><img src="/images/jira.png"	/> </label>
        </a>
      </li>
      <li>
        <a  href="http://192.168.1.70:8888" target="_blank">
          <input id='9' type='checkbox'>
          <label title="gitlab"><img src="/images/gitlab.png" /></label>
        </a>
      </li>
    </ul>
    <button>米仓金服</button>
  </div>
  <script src="/js/jquery.min.js"></script>
  <script src="/js/home.js"></script>
  <script src="/js/word.js"></script>
</body>
</html>
