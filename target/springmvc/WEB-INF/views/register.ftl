
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    
    <!-- Title Page-->
    <title>Register</title>
    <!-- Font special for pages-->
	<script src="./jquery-ui.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    
    
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="./css/register.css" rel="stylesheet" media="all">
	<link href="./css/address.css" rel="stylesheet" media="all">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
    <style>

        <#include "resources/css/register.css"  />
        <#include "resources/css/address.css"  />
        body{background-color:#F9F9FC;font-family: 'Roboto', sans-serif;font-size:14px;}
        .select2-container {
            padding: 0;
            border: none;
        }
        h3{margin-bottom:0;}
        .card{box-shadow: 0 0 13px 0 rgba(82,63,105,.05);background-color: #fafafa;margin-bottom: 20px;border-radius: 4px;padding: 20px;}
        .select2-choice {
            display: block;
            width: 100%;
            height: calc(1.5em + .75rem + 2px) !important;
            padding: .375rem .75rem !important;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5 !important;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: .25rem;
            transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
        }
        label.error{
            color:red;
        }
        </style>

</head>

            <#--  <c:set var="user" scope="session" value="${requestScope.detailsofUser}"/>   -->
            
            
<body>
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                    <h2 class="title">User Registration form</h2>
            </div>
                
                <#--  <p style="text-align:center"><c:out value="${errMsg}"/></p>  -->
                <div class="card-body">
                    <form action="Signup" method="POST" id="register" enctype="multipart/form-data">
                        <div class="form-row m-b-55">
                            <div class="name">Name</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                       <div class="input-group-desc d-none">
                                            <input class="input--style-5" type="text" name="id" id="id" value="<#if user?? && user.id??>${user.id}<#else>0</#if>">
                                            <label class="label--desc">first name</label>
                                        </div>
                                        <div class="input-group-desc d-none">
                                            <input class="input--style-5" type="text" name="roles[0].role" id="id" value="1">
                                            <label class="label--desc">first name</label>
                                        </div>
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="firstName" id="firstName" value="<#if user?? && user.firstName??>${user.firstName}</#if>">
                                            <label class="label--desc">first name</label>
                                        </div>
                                    </div>
                                    
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="lastName"  id="lastName" value="<#if user?? && user.lastName??>${user.lastName}</#if>">
                                            <label class="label--desc">last name</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="name">Email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" id="email" name="email" value="<#if user?? && user.email??>${user.email}</#if>">
                                </div>
                            </div>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">Phone</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="number" name="phone" id="phone" value="<#if user?? && user.phone??>${user.phone}</#if>">
                                            <label class="label--desc">Phone Number</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row p-t-20">
                            <div class="name">Gender</div>
                            Male:<input type="radio" name="gender" value="M" id="Gmale" <#if user?? && user.gender=="M">checked="checked"</#if>>
                           Female:<input type="radio" name="gender" value="F" id="Gfemale" <#if user?? && user.gender=="F">checked="checked"</#if>>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">DOB</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="date" name="dob" id="dob" value="<#if user?? && user.dob??>${user.dob}</#if>">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                
                        <div class="form-row m-b-55">
                            <div class="name">Password</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="password" name="pass" id="pass" value="<#if user?? && user.pass??>${user.pass}</#if>">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                         <div class="form-row m-b-55">
                            <div class="name">Profile image</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input type="file" id="profilepic" name="profileimage" accept="image/*">
                                            <img src="data:image/jpg;base64,<#if user??>${user.profilepic}</#if>" width="240" height="300"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                   
                        

                        <div class="form-row m-b-55">
                            <div class="name">Security question</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <p>What was your first school name?</p>
                                            <input class="input--style-5" type="text" name="securityAns" id="securityAns" value="<#if user?? && user.securityAns??>${user.securityAns}</#if>">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

						<div class="container" style="margin:150px auto">
    <div class="margin-t-md">
        <div class="customer-form">


           
                    
                <div id="main-container">
                
                <#if user??>
                <#list user.addresses as x>
                    <script>

                    </script>
                    <div class="panel card container-item">

                        
                        <div class="panel-body">
                            <div class="panel-body">
                                       
                                <div class="row">
                                    <div class="col-sm-6">
                                       <div class="form-group d-none">
                                            <label class="control-label" for="address_id_0">Address line 1</label>
                                            <input type="text" id="address_id_0" class="form-control" name="aid" value="<#if user?? >${x.aid}</#if>">
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_one_0">Address line 1</label>
                                            <input type="text" id="address_line_one_0" class="form-control" name="addresses[0].addressLine1" maxlength="255" required value="<#if user?? >${x.addressLine1}</#if>">
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_two_0">Address line 2</label>
                                            <input type="text" id="address_line_two_0" class="form-control" name="addresses[0].addressLine2" maxlength="255" required value="<#if user?? >${x.addressLine2}</#if>">
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="city_0">city</label>
                                            <select id="city_0" class="form-control" name="addresses[0].city" >
												<option value="Ahmedabad"<#if user?? & x.city=="Ahmedabad"> selected </#if>> Ahmedabad</option>
												<option value="Delhi"<#if user?? & x.city=="Delhi"> selected </#if>> Delhi</option>
												<option value="Mumbai"<#if user?? & x.city=="Mumbai"> selected </#if>> Mumbai</option>
												<option value="Jaipur"<#if user?? & x.city=="Jaipur"> selected </#if>> Jaipur</option>
												<option value="banglore"<#if user?? & x.city=="banglore"> selected </#if>> banglore</option>
												
										</select>

                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="state_0">State</label>
                                            <select id="state_0" class="form-control" name="addresses[0].state" >
												<option value="gujrat" <#if user?? & x.state=="gujrat"> selected </#if>> gujrat</option>
												<option value="MP" <#if user?? & x.state=="MP"> selected </#if>> MP</option>
												<option value="maharastra" <#if user?? & x.state=="maharastra"> selected </#if>> maharastra</option>
												<option value="Delhi" <#if user?? & x.state=="Delhi"> selected </#if>> Delhi</option>
												<option value="Rajasthan" <#if user?? & x.state=="Rajasthan"> selected </#if>> Rajasthan</option>
												
										</select>
                                                             
                                        </div>
                                    </div>
                                                        
                                </div>
                                <br>
                                <div class="col-sm-6">
                                        <div class="form-group">
                                        <input class="form-control" id="pincode_0"  name="addresses[0].pincode" placeholder="pincode" required value="<#if user?? >${x.pincode?string.computer}</#if>">
                                        </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div>
                                            <a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </#list>
                <#else>
                
                    
                    <div class="panel card container-item"> 

                        
                        <div class="panel-body">
                            <div class="panel-body">

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group d-none">
                                            <label class="control-label" for="address_id_0">Address line 1</label>
                                            <input type="text" id="address_id_0" class="form-control" name="aid" >
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_one_0">Address line 1</label>
                                            <input type="text" id="address_line_one_0" class="form-control" name="addresses[0].addressLine1" maxlength="255" required value="<#if user?? >${x.addressLine1}</#if>">
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_two_0">Address line 2</label>
                                            <input type="text" id="address_line_two_0" class="form-control" name="addresses[0].addressLine2" maxlength="255" required>
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="city_0">city</label>
                                            <select id="city_0" class="form-control" name="addresses[0].city">
												<option value="Ahmedabad">Ahmedabad</option>
												<option value="Delhi">Delhi</option>
												<option value="Mumbai">Mumbai</option>
												<option value="Jaipur">Jaipur</option>
												<option value="banglore">banglore</option>
												
										</select>

                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="state_0">State</label>
                                            <select id="state_0" class="form-control" name="addresses[0].state">
												<option value="gujrat">Gujrat</option>
												<option value="MP">MP</option>
												<option value="maharastra">maharastra</option>
												<option value="Delhi">Delhi</option>
												<option value="Rajasthan">Rajasthan</option>
												
										</select>
                                                             
                                        </div>
                                    </div>
                                                        
                                </div>
                                <br>
                                <div class="col-sm-6">
                                        <div class="form-group">
                                        <input class="form-control" id="pincode_0" type="number" name="addresses[0].pincode" placeholder="pincode" required>
                                        </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div>
                                            <a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                
                </#if>
                </div>
                <div class="card">
                    <div>
                       <div > <a type="button" class="btn btn-success btn-sm" id="add-more"   role="button"><i class="fa fa-plus"></i> Add more address</a></div>
                       <div id="add-more-hidden" class="d-none"> <div type="button" class="btn btn-success btn-sm" ><i class="fa fa-plus"></i> Add more address</div></div>
                       <br>
                        <input type="submit" id="btn-submit" class="btn btn-primary btn-sm" value="Submit">
                    </div>
                </div> 
        </div>
    </div>
</div>              
					<br><br>
				</th> 
                    </form>

                </div>
            </div>
        </div>
    </div>  
	
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
<script>
    <#include "resources/js/cloneData.js"  />
</script>
<script>

 $('a#add-more').cloneData({
        mainContainerId: 'main-container', // Main container Should be ID
        cloneContainer: 'container-item', // Which you want to clone
        removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
        removeConfirm: true, // default true confirm before delete clone item
        removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
        //append: '<a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>', // Set extra HTML append to clone HTML
        minLimit: 1, // Default 1 set minimum clone HTML required
        maxLimit: 0, // Default unlimited or set maximum limit of clone HTML
        defaultRender: 1,
        init: function () {
            console.info(':: Initialize Plugin ::');
        },
        beforeRender: function () {
            console.info(':: Before rendered callback called');
        },
        afterRender: function () {
            console.info(':: After rendered callback called');
            //$(".selectpicker").selectpicker('refresh');
        },
        afterRemove: function () {
            console.warn(':: After remove callback called');
        },
        beforeRemove: function () {
            console.warn(':: Before remove callback called');
        }

    });
</script>
<script>
    <#include "resources/js/Validation.js"  />
</script>   
 
</body>

</html>


