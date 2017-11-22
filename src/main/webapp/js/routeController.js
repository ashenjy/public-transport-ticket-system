angular.module('app').controller('routeController', function($scope, $http, $window)
{
    //create new location route
    $scope.createRoute=function ()
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

        $http.post("/createRoute", data, config).then(function (response) {
            console.log("Route created successfully");
            $window.alert("Route created successfully");
        }, function error(response) {
            console.log("Route creation failed");
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