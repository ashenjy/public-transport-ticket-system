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

            }).state("review",{
                url:"/reviewComplaint",
                templateUrl:"/views/passenger/complaintsList.html",
                controller:"mailController"


            });

        })
//        .constant("globalConfig", {
//            apiAddress: 'http://localhost:8080/'
//        });
})();