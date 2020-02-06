<%@ page import="org.Database" %>
<%@ page  import= "java.sql.Connection"%>
<%@ page import= "java.sql.*"%>
<%@ page import ="java.util.Arrays"%>
<%@ page import ="java.util.Scanner"%>
<%@ page import ="java.util.logging.Level"%>
<%@ page import ="java.util.logging.Logger"%>
 <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

  <title>Craneware</title>

</head>

<body>

<!-- search options left-->
  <div class="container-fluid">
    <!--<h1 style="text-align:center">Craneware</h1>-->
<div style="background-image: url('background.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;">
    <!-- Image and text -->
    <nav class="navbar navbar-light bg-light">
      <a class="navbar-brand" href="#">
        <img src="craneware_Logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Craneware
      </a>
    </nav>
<p style ="height:3px;"> </p>
    <h1 class="display-4" style="text-align:center">Search and compare hospitals acorss the US</h1>
<p style ="height:3px;"> </p>
    <div class="row">
        
      <div class="card" style="width: 85rem; margin-left: auto; margin-right: auto;">
        <div class="card-body">

          <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
              <a class="nav-link active" id="search-tab" data-toggle="tab" href="#search" role="tab" aria-controls="search" aria-selected="true">Search</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="filter-tab" data-toggle="tab" href="#filter" role="tab" aria-controls="filter" aria-selected="false">Filter</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="bestForYou-tab" data-toggle="tab" href="#bestForYou" role="tab" aria-controls="top" aria-selected="false">Best For You</a>
            </li>
          </ul>
          <div class="tab-content" id="myTabContent">

            <!-- Search tab -->
            <div class="tab-pane fade show active" id="search" role="tabpanel" aria-labelledby="search-tab">
              <p style ="height:10px;"> </p>
              <p style="margin-left:2rem">- Use as many or as little of the search options as you want to refine your search..</p>

              <p style ="height:3px;"> </p>

              <!-- Checkbox -->
              <div class="checkbox"style="margin-left:2rem">
                <label><input type="checkbox" onclick="checkboxClickSearch()" value=""> Use your current location</label>
              </div>
              <script>
              function checkboxClickSearch() {
                //disable address and zip search boxes when using device location
                document.getElementById("addressInputSearch").disabled = !document.getElementById("addressInputSearch").disabled;
                document.getElementById("zipInputSearch").disabled = !document.getElementById("zipInputSearch").disabled;
                //clear any input that is already in the box
                document.getElementById('addressInputSearch').value = '';
                document.getElementById('zipInputSearch').value = '';
              }
              </script>

              <form class="form-inline" style="margin-left:2rem" method="get" >

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by hospital name..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="nameInputSearch" >
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="address..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="addressInputSearch" name="addressInputSearch">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="zip code..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="zipInputSearch">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="procedure name..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="procedureNameInputSearch">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="procedure code..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem" id="procedureCodeInputSearch">
                </div>
               <p></p>
                  <!-- Search button -->
              <p style = "text-align:center;"><button type="submit" value="javaServerJSP" class="btn btn-outline-primary" style="width:220px">Search</button></p>
              </form>

              

              
              <p></p>

            </div>

            <!-- Filter tab -->
            <div class="tab-pane fade" id="filter" role="tabpanel" aria-labelledby="filter-tab">

              <p style ="height:10px;"> </p>
              <p style="margin-left:2rem">- Use as many or as little of the search options as you want to refine your search..</p>

              <p style ="height:3px;"> </p>

              <!-- Checkbox -->
              <div class="checkbox"style="margin-left:2rem">
                <label><input type="checkbox" onclick="checkboxClickFilter()" value=""> Use your current location</label>
              </div>

              <script>
              function checkboxClickFilter() {
                // dissable address and zip search boxes if they are not already disabled
                document.getElementById("addressInputFilter").disabled = !document.getElementById("addressInputFilter").disabled;
                document.getElementById("zipInputFilter").disabled = !document.getElementById("zipInputFilter").disabled;
                //clear any input that is already in the box
                document.getElementById('addressInputFilter').value = '';
                document.getElementById('zipInputFilter').value = '';
              }
              </script>

              <form class="form-inline" style="margin-left:2rem">

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by hospital name..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="nameInputFilter">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by address..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="addressInputFilter">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by zip code..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="zipInputFilter">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by procedure name..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem;" id="procedureNameInputFilter">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by procedure code..." aria-label="Search" style="width: 15rem; margin-left: 0.5rem" id="procedureCodeInputFilter">
                </div>
              </form>

              <p></p>

              <h4>Filter</h4>

              <!-- dropdown select price range -->

                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text">$</span>
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">$</span>
                    </div>
                    <input type="text" class="form-control" placeholder="Maximum Price..." aria-label="Amount (to the nearest pound)">
                    <div class="input-group-append">
                      <span class="input-group-text">.00</span>
                    </div>
                  </div>
                  <input type="text" class="form-control" placeholder="Maximum Price..." aria-label="Amount (to the nearest pound)">
                  <div class="input-group-append">
                    <span class="input-group-text">.00</span>
                  </div>
                </div>

                <p></p>


                <!-- dropdown select distance range -->
              <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Maximum Distance..." aria-label="Maximum Distance" aria-describedby="basic-addon2">
                <div class="input-group-append">
                  <span class="input-group-text" id="basic-addon2">Miles</span>
                </div>
              </div>

                <p></p>

                <h4>Sort (pick one)</h4>

                <!-- dropdown select highest to lowest price -->
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Price</label>
                  </div>
                  <select class="custom-select" id="inputGroupSelect01">
                    <option selected>Choose...</option>
                    <option value="1">None</option>
                    <option value="2">Low - High</option>
                    <option value="3">High - Low</option>
                  </select>
                </div>

                <p></p>
                <!-- dropdown select closest to furthest distance -->
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Distance</label>
                  </div>
                  <select class="custom-select" id="inputGroupSelect01">
                    <option selected>Choose...</option>
                    <option value="1">None</option>
                    <option value="2">Low - High</option>
                    <option value="3">High - Low</option>
                  </select>
                </div>

                <!-- Search button -->
                <p style = "text-align:center;"><button type="submit" class="btn btn-outline-primary" style="width:220px">Search</button></p>
                <p></p>



            </div>

            <!-- Best For You tab -->
            <div class="tab-pane fade" id="bestForYou" role="tabpanel" aria-labelledby="bestForYou-tab">

              <p style ="height:10px;"> </p>

              <p style="margin-left:2rem">- Using your search requests, we will put together a top 10 list of Hospitals matching your needs</p>

              <p style="margin-left:2rem">- You can use as many or as little of the options as you like..</p>
              <p style ="height:3px;"> </p>


              <div class="checkbox"style="margin-left:2rem">
                <label><input type="checkbox" value=""> Use your current location</label>
              </div>

              <form class="form-inline" style="margin-left:2rem">

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by hospital name..." aria-label="Search" style="width: 18rem; margin-left: 0.5rem;" id="nameInput">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by address..." aria-label="Search" style="width: 18rem; margin-left: 0.5rem;" id="addressInput">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by zip code..." aria-label="Search" style="width: 18rem; margin-left: 0.5rem;" id="zipInput">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by procedure name..." aria-label="Search" style="width: 18rem; margin-left: 0.5rem;" id="procedureNameInput">
                </div>

                <div class="md-form mt-0">
                  <input class="form-control" type="text" placeholder="Search by procedure code..." aria-label="Search" style="width: 18rem; margin-left: 0.5rem" id="procedureCodeInput">
                </div>
              </form>

              <p></p>

              <form class="form-inline" style="margin-left:2rem">

                <div class="input-group mb-3" style="width: 44rem; margin-left: 0.5rem">
                  <div class="input-group-prepend">
                    <span class="input-group-text">$</span>
                  </div>
                  <input type="text" class="form-control" placeholder="Ideal Price..." aria-label="Amount (to the nearest pound)">
                  <div class="input-group-append">
                    <span class="input-group-text">.00</span>
                  </div>
                </div>

                <p></p>

                  <!-- dropdown select distance range -->
                <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="Ideal Distance..." aria-label="Maximum Distance" aria-describedby="basic-addon2" style="width: 44rem; margin-left: 0.5rem">
                  <div class="input-group-append">
                    <span class="input-group-text" id="basic-addon2">Miles</span>
                  </div>
                </div>

                <p></p>

              </form>


            </div>
          </div>

          </div>
            </div>
          </div>
            <div>
            <p style ="height:15px;"> </p>
          </div>

          </div>

          <hr>

          <div class="container-fluid">
       <div class="row">
         <div class = "col-sm-6">
           <!-- search results -->
           <h1 id="searchResults">Your Results</h1>
           
            <% System.out.println("blob");
                Database db = new Database();
            String[] search = request.getParameterValues("addressInputSearch");
            System.out.println("line 316");
            if(search!=null){
                System.out.println("not null");
            
                ResultSet result = db.runSearchConditionP(search[0]);
            //String[] conditions = db.parseCondition(result);
            //String[] addresses = db.parseAddress(result);
            //String[] provNames = db.parseProviderName(result);
            //Double[] avgMeds = db.parseAvgMed(result);
            int counter = 0;
                while(result.next() && counter<10){
                    
                
            %>
            <h1>test</h1>
                <div class ="container p-3 my-3 border">
                    <div class ="row">
                        <h1><%= result.getString("providerName") %></h1>
                  <p><%= result.getString("providerStreetAddress") %></p>
                  <p><%= result.getString("HRRDescription")%></p>
                  <p>Average medical care payments: $<%= result.getDouble("Average_Medicare_Payments")%></p>
                    </div>

                </div>
                    
                    <% counter++;
                        }
                }
            %>
           

           

           <a id="display"></a>

         </div>

         <div class = "col-sm-6">

           <div class="card sticky-top" style = "text-align: center; padding-left:40px;">
             <!-- map -->
             
               <iframe src="https://www.google.com/maps/d/u/0/embed?mid=1rKyzzZcUR_zFjwAkFWqQOIoJ7B4iIbvl" width="640" height="480"></iframe>

           </div>


         </div>

       </div>


     </div>


   </body>

</html>
<!--
            

