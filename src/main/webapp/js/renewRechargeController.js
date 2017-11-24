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

        //current datetime
        var date=new Date();

        var data = {
            category: $scope.category,
            paymentType: $scope.paymentType,
            amount:$scope.amount,
            paymentDate:date,
            nic:sessionStorage.nic
        };

        $http.post("/createPaymentL", data, config).then(function (response) {
            console.log("createPaymentL created successfully");
            $window.alert("Payment made successfully");
            $scope.getPaymentL();
        }, function error(response) {
            console.log("Payment failed");
        });

        $scope.category="";
        $scope.paymentType="";
        $scope.amount="";

    }

    //get all the routes
    $scope.getPaymentL=function ()
    {
        $http.get("/getPaymentL").then(function (response) {
            console.log("Payment loaded successfully");
            $scope.payment=response.data;
        }, function error(response) {
            console.log("Failed to load getPaymentL");
        });
    }

    //execute function to get all the routes
    $scope.getPaymentL();

});