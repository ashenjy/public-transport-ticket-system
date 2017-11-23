(function() {
    'use strict';

    angular.module('app', [
        "ui.router",
        "ui.bootstrap"
    ])
        .config(function($stateProvider, $urlRouterProvider)
        {
            $urlRouterProvider.otherwise("/");

            $stateProvider.state("addEmployee", {
                    url: "/addEmployee",
                    templateUrl: "/views/admin/addEmployee.html",
                    controller: "employeeController"
                }).state("login",{
                url:"/login",
                templateUrl:"login.html",
                controller:"loginController"

                //----Ashen-----------------------------
                }).state("passengerReg",{
                url:"/registration",
                templateUrl:"/views/passenger/passengerRegistration.html",
                controller:"passengerController"

            })

                .state("manageRoute",{
                    url:"/manageRoute",
                    templateUrl:"/views/admin/manageRoute.html",
                    controller:"routeController"
                })

                .state("manageRoutePrice",{
                    url:"/manageRoutePrice",
                    templateUrl:"/views/admin/manageRoutePrices.html",
                    controller:"distanceCostController"
                });

        })
//        .constant("globalConfig", {
//            apiAddress: 'http://localhost:8080/'
//        });
})();