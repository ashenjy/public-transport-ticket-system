angular.module('app').controller('renewRechargeController', function($scope, $http, $window)
{
    //dropdown
    $scope.categories=["Renew Card", "Recharge Card Balance"];
    $scope.paymentTypes=["Cash", "Credit Card", "Debit Card"];

    //create new location route
    $scope.createRenewRecharge=function ()
    {
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            source: $scope.source,
            destination: $scope.destination,
            distance:$scope.distance
        };

        $http.post("/createPaymentL", data, config).then(function (response) {
            console.log("createPaymentL created successfully");
            $window.alert("Payment made successfully");
            $scope.getRoutes();
        }, function error(response) {
            console.log("Payment failed");
        });

        $scope.source="";
        $scope.destination="";
        $scope.distance="";
    }

    //get all the routes
    $scope.getRoutes=function ()
    {
        $http.get("/getRoutes").then(function (response) {
            console.log("Routes loaded successfully");
            $scope.routes=response.data;
        }, function error(response) {
            console.log("Failed to load routes");
        });
    }

    //execute function to get all the routes
    $scope.getRoutes();

});