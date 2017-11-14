angular.module('app').controller('employeeController', function($scope, $http, $window)
{
    //IT15021076
    $scope.addEmployee=function ()
    {
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            empNo:$scope.empNo,
            name:$scope.name,
            nic:$scope.nic,
            mobile:$scope.mobile,
            email:$scope.email,
            password:$scope.password,
            //password:$scope.encryptPassword(password),
            salary:$scope.salary,
            type:$scope.type
        }

        console.log(data);

        $http.post("/addEmployee", data, config).then(function (response) {
            $window.alert("Failed to add Employee!");
        }, function (response) {
            $window.alert("Employee Added to the System");
            $scope.getEmployees();
            //window.location.reload();
        });


        $scope.empNo="";
        $scope.name="";
        $scope.nic="";
        $scope.mobile="";
        $scope.email="";
        $scope.password="";
        $scope.salary="";
        $scope.type="";


    }

    //function to get all the employees to the main view
    $scope.getEmployees=function ()
    {
        $http.get("/getEmployees").then(function (response)
        {
            $scope.employees = response.data;
            console.log("Employee List loaded");
        }, function (response)
        {
            $scope.getResultMessage = "Failed to load!";
        });
    }


    //function to encrypt password
    $scope.encryptPassword=function ()
    {

        var pwdis = $scope.password;

      //  var hashedPassword = $scope.password;
       // return hashedPassword;
     //   return $scope.password;
    }

    //clear screen
    $scope.clearInput=function ()
    {
        $scope.empNo="";
        $scope.name="";
        $scope.nic="";
        $scope.mobile="";
        $scope.email="";
        $scope.password="";
        $scope.salary="";
        $scope.type="";
    }

    //cancel edit
    $scope.cancelbtn=function ()
    {
        $scope.clearInput();

        $scope.hideSaveBtn=false;
        $scope.showCancelBtn=false;
        $scope.showUpdateBtn=false;
    }

    //edit a employee by getting it to load
    $scope.getEditEmployee=function (eid, empNo, name, nic, mobile, email, salary, type)
    {
        $scope.eid=eid;
        $scope.empNo=empNo;
        $scope.name=name;
        $scope.nic=nic;
        $scope.mobile=mobile;
        $scope.email=email;
        $scope.salary=salary;
        $scope.type=type;

        $scope.hideSaveBtn=true;
        $scope.showCancelBtn=true;
        $scope.showUpdateBtn=true;
    }

    //edit employee from data loaded
    $scope.editEmployee=function ()
    {
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            empNo: $scope.empNo,
            name: $scope.name,
            nic:$scope.nic,
            mobile:$scope.mobile,
            email: $scope.email,
            salary: $scope.salary,
            type: $scope.type

        };

        console.log(data);
        var employeeIdMain=parseInt($scope.eid);
        $http.put("/editEmployee?eid=" + employeeIdMain, data, config).then(function (response) {
            $window.alert("Failed to update Employee!");
        }, function (response) {
            $window.alert("Employee updated sucessfully!");
            $scope.getEmployees();
        });

        $scope.clearInput();

        $scope.hideSaveBtn=false;
        $scope.showCancelBtn=false;
        $scope.showUpdateBtn=false;
    }


    //delete a employee
    $scope.deleteEmployee=function (eid)
    {
        if (confirm("Are You Really Want to Delete This Event?")) {

            $http.delete("/deleteEmployee?eid="+eid).then(function (response)
            {
                console.log("Employee deleted!");
                $scope.getEmployees();
            }, function (response)
            {
                $scope.getResultMessage = "Failed to delete!";
            });
        }
        else
        {
            console.log("Employee delete cancel!");
        }
    }

    //reset a employee password for its default
    $scope.resetPassword=function (eid)
    {
        //
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        //
        if (confirm("Reset Employee Password to Default : 'hotel_1234' ?")) {
           // $scope.getResultMessage = "Reset Password Success!";
            console.log("Password Reset!");
            $http.put("/resetPassword?eid="+eid,config).then(function (response)
            {
                console.log("Password Reset!");
                $scope.getEmployees();
            }, function (response)
            {
                $scope.getResultMessage = "Failed to Reset Password!";
            });
        }
        else
        {
            console.log("reset password cancel!");
        }
    }

    //call the function to get values from database to table
    $scope.getEmployees();
});