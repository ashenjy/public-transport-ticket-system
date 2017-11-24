angular.module('app').controller('loginController', function($scope, $http, $window)
{
    //populate dropdown
    $scope.types = ["Staff", "Administrator"];

    //function to get employee of the selected nic
    $scope.findEmployee=function ()
    {
        $http.get("/findEmployee?nic=" + $scope.nic + "&password=" + $scope.password +
            "&type=" + $scope.type).then(function success(response)
        {
            $scope.employee = response.data;
            //console.log("Employee found.");

            if(response.data=="")
            {
                console.log("invalid u/p");
                $window.alert("Invalid username/password/type!");
            }
            else
            {
                console.log(response.data);

                sessionStorage.name=response.data.name;
                sessionStorage.email=response.data.email;
                sessionStorage.nic=response.data.nic;
                sessionStorage.type=response.data.type;

                console.log("User Logged in :" + sessionStorage.name);
                window.location.href="home.html";

            }
        }, function error(response)
        {
            $scope.getResultMessage = "Failed to load!";
        });
    }
 
});