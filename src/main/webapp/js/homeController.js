angular.module('app').controller('homeController', function($scope, $http, $window)
{
    $scope.user=sessionStorage.name;
    console.log("User in homepage: " + $scope.user);

    $scope.logout=function ()
    {
        window.location.href="login.html";
    }
});