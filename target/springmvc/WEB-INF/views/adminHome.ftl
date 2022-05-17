<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <link href="https://fonts.googleapis.com/css?family=Quicksand:400,600,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <#--  <link rel="stylesheet" href="./css/adminHome.css">  -->

    <title>Website Menu #10</title>
    <style>
   <#include "resources/css/adminHome.css"  />
   </style>
  </head>
  <body>


    <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
          <div class="site-mobile-menu-close mt-3">
            <span class="icon-close2 js-menu-toggle"></span>
          </div>
        </div>
        <div class="site-mobile-menu-body"></div>
      </div>



      <header class="site-navbar site-navbar-target bg-white" role="banner">

        <div class="container">
          <div class="row align-items-center position-relative">

            <div class="col-lg-4">
              <nav class="site-navigation text-right ml-auto " role="navigation">
                <ul class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
                  <li class="active"><a href="adminHome1.jsp" class="nav-link">Home</a></li>
                  <li><a href="EditDetails">Profile</a></li>
                  <li><button id="showUserDetails" class="btn btn-info">showUserDetails</button></li>
                </ul>
              </nav>
            </div>
            <div class="col-lg-4 text-center">
              <div class="site-logo">
                <a href="adminHome1.jsp">AdminHome</a>
              </div>
                

              <div class="ml-auto toggle-button d-inline-block d-lg-none"><a href="#" class="site-menu-toggle py-5 js-menu-toggle text-black"><span class="icon-menu h3 text-black"></span></a></div>
            </div>
            <div class="col-lg-4">
              <nav class="site-navigation text-left mr-auto " role="navigation">
                <ul class="site-menu main-menu js-clone-nav ml-auto d-none d-lg-block">
                  <li><a href="register">AddNewUser</a></li>
                  <li><a href="torecentlyregistered">newUsersList</a></li>
                  <li><a href="Logout">Logout</a></li>

                </ul>
              </nav>
            </div>
            

          </div>
        </div>
          
      </header>
    
 <div id="tablediv">
<table cellspacing="0" id="showData"> 
    <thead> 
    <tr>
        <th style="text-align:start; width:33.3% !important;">ID</th> 
        <th style="text-align:start; width:33.3% !important;">Name</th> 
        <th style="text-align:start; width:33.3% !important;">Email</th> 
        <th style="text-align:start; width:33.3% !important;">EDIT</th> 
        <th style="text-align:start; width:33.3% !important;">DELETE</th> 
            
           </tr>    
    </thead>
    <tbody>
        
    </tbody> 
</table>
</div>
  
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showUserDetails").click(function(event){
           $.post('UsersDetails',function(data) {
            if(data!=null){
                $("#showData").find("tr:gt(0)").remove();
                
                $("#showData").DataTable({
                    data : data,
                    columns: [
                        { data: 'id'},
                        { data: 'firstName'},
                        { data: 'email'},
                        { data: null, title: 'Action', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="EditUserDetails(\'' + item.email + '\')" class="btn btn-info" >EDIT</button></div> ' } },
                        { data: null, title: 'DELETE', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="DeleteUser(' + item.id + ')"  class="btn btn-danger">DELETE</button></div>' } },
                    ],

                });
            }
            else{
                alert("Data is coming null");
            }
            });

            $("#tablediv").show();          
  });      
            
});
function DeleteUser(userId) {
            
			$.post("DeleteUser", {
				"userId" : userId
			}, function() {
				window.location.reload();
                ('#showUserDetails').click();
			}
);              
     };     


function EditUserDetails(email) {
    alert("email:"+ email);
    $.post("emailFromUser",{"email": email},function() {
        window.location.href = "register"
    });
        
}  
 
            
</script>

    <#--  <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.sticky.js"></script>
    <script src="js/main.js"></script>  -->
  </body>
</html>