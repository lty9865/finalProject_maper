<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!DOCTYPE html>
      <html>

      <head>
         <meta charset="UTF-8">
         <title>회원 관리</title>
         <script type="text/javascript" src="../../Resources/javascript/Member.js"></script>
      </head>
     
      <body>
         <h2>회원 가입</h2>
         '*' 표시 항목은 필수 입력 항목입니다.
         <form action="Join.do" method="post" name="frm">
            <table>
               <tr>
                  <td>아이디</td>
                  <td>
                     <input type="text" name="userid" size="20" id="userid">*
                     <input type="hidden" name="reid" size="20">
                     <input type="button" value="중복체크" onclick="idCheck()">
                  </td>
               </tr>
               <tr>
                  <td>암호</td>
                  <td><input type="password" name="password" size="20">*</td>
               </tr>
               <tr height="30">
                  <td width="80">암호 확인</td>
                  <td><input type="password" name="confirmPassword" size="20">*
                  </td>
               </tr>
               <tr>
                  <td>이메일</td>
                  <td><input type="text" name="email" size="20"></td>
               </tr>

               <div class="row">
                  <div class="col">
                     <select id="year" name="year" class="form-control">
                        <option value="">년</option>
                        <c:forEach var="i" begin="1940" end="2010">
                           <option value="${i}">${i}</option>
                        </c:forEach>
                     </select>
                  </div>

                  <div class="col">
                     <select id="month" name="month" class="form-control">
                        <option value="">월</option>
                        <c:forEach var="i" begin="1" end="12">
                           <c:choose>
                              <c:when test="${i lt 10 }">
                                 <option value="0${i}">0${i}</option>
                              </c:when>
                              <c:otherwise>
                                 <option value="${i}">${i}</option>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                     </select>
                  </div>

                  <div class="col">
                     <select id="day" name="day" class="form-control">
                        <option value="">일</option>
                        <c:forEach var="i" begin="1" end="31">
                           <c:choose>
                              <c:when test="${i lt 10 }">
                                 <option value="0${i}">0${i}</option>
                              </c:when>
                              <c:otherwise>
                                 <option value="${i}">${i}</option>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                     </select>
                  </div>
               </div>
               <td colspan="2" align="center">
                  <input type="submit" value="확인" onclick="return joinCheck()">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="reset" value="취소">
               </td>
               <tr>
                  <td colspan="2">${message}</td>
               </tr>
            </table>
         </form>
      </body>
      </html>