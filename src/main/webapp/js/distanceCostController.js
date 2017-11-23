angular.module('app').controller('distanceCostController', function($scope, $http, $window)
{
    //create new DistanceCost
    $scope.createDistanceCost=function ()
    {
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            distance: $scope.distance,
            cost: $scope.cost
        };

        $http.post("/createDistanceCost", data, config).then(function (response) {
            console.log("Distance cost created successfully");
            $window.alert("Cost added for distance successfully");
            $scope.getDistanceCost();
        }, function error(response) {
            console.log("distance cost creation failed");
        });

        $scope.distance="";
        $scope.cost="";
    }

    //get all the DistanceCost
    $scope.getDistanceCost=function ()
    {
        $http.get("/getDistanceCost").then(function (response) {
            console.log("Distance costs loaded successfully");
            $scope.distanceCost=response.data;
        }, function error(response) {
            console.log("Failed to load distanceCost");
        });
    }

    //execute function to get all the DistanceCost
    $scope.getDistanceCost();

});