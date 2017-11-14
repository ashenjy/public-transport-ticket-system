angular.module('app').controller('loginController', function($scope, $http, $window)
{

    //function to get employee of the selected nic
    $scope.findEmployee=function ()
    {
        $http.get("/findEmployee?nic=" + $scope.nic + "&password=" + $scope.password + "&type=" + $scope.type).then(function (response)
        {
            $scope.employee = response.data;
            //console.log("Employee found.");

            if(response.data=="")
            {
                console.log("invalid u/p");
                $window.alert("Invalid username/password/type");
            }
            else
            {
                console.log(response.data);

                sessionStorage.empNo=response.data.empNo;
                sessionStorage.name=response.data.name;
                sessionStorage.email=response.data.email;
                sessionStorage.nic=response.data.nic;
                sessionStorage.type=response.data.type;

                console.log("User Logged in :"+response.data.nic);
                location.href="home.html";

                $scope.gotname = response.data.name;
              //  console.log("here"+$scope.gotname);
            }
        }, function (response)
        {
            $scope.getResultMessage = "Failed to load!";
        });
    }

    /*
    function getmyname() {

        return sessionStorage.name;

        //SAVE VAUE
        $window.sessionStorage.setItem("SavedString",sessionStorage.name);

        //RETRIEVE VALUE
        $scope.mynameis = $window.sessionStorage.getItem("SavedString");
    }*/

});