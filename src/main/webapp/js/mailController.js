(function() {
    'use strict';

    angular
        .module('app')
        .controller('mailController', Controller);

    Controller.$inject = ['$http','$scope','$uibModal'];

    function Controller($http,$scope,$uibModal) {

        console.log("mail controller");



        var vm = this;

        vm.complaints = [];

        vm.getAll = getAll;

        init();

        function init(){
            getAll();
        }


        //Pagination
        vm.currentPage = 1;
        vm.itemsPerPage = 5;



        function getAll(){
            console.log("getall function called");
            var url = "/complainss/all";
            var mailPromise = $http.get(url);
            mailPromise.then(function(response){
                vm.complaints = response.data;
                console.log("Successfully got complaints data", vm.complaints);
            });
        }



        //--------------------------------------------------------------------



        vm.openCreateNewDialog = function(complaintsObj){


            var newScope = $scope.$new();


            //--------------------------------------------------------------------------

            var dialogOpts = {
                scope: newScope,
                templateUrl: '/views/passenger/mailUIModal.html',
                backdrop: 'static',
                size: 'lg'
            };


            var instance = $uibModal.open(dialogOpts);

            newScope.closeDialog = function(){
                instance.close();
                newScope.$destroy();
            };

            newScope.email = {
                mailFrom : "Public-Transport-Mailer-System",
                mailTo : complaintsObj.pemail,
                mailSubject: "",
                mailContent: "Dear " + complaintsObj.passengerName +","
            };


            newScope.mailHist = false;
            newScope.mails = {};


                newScope.getAllMails = function(){
                    console.log("getallMail function called");
                    var url = "/mail/all";
                    var mailPromise = $http.get(url);
                    mailPromise.then(function(response){
                        newScope.mails = response.data;
                        console.log("Successfully got mail data", newScope.mails);
                    });
                }

            if(newScope.mailHist){
                newScope.getAllMails();
            }

                newScope.sendEmail= function() {

                    console.log("sendEmail function called");
                    var todayDate = new Date();

                        var url = "/mail/sendEmail";

                        var data = {

                            mailFrom: newScope.email.mailFrom,
                            mailTo: newScope.email.mailTo,
                            mailSubject: newScope.email.mailSubject,
                            mailContent: newScope.email.mailContent,
                            mailDate: todayDate
                        };

                        console.log("saving frontend data ", data);

                        $http.post(url, data).then(function (response) {

                            window.alert("Successfully Sent!");
                            newScope.mails = response.data;
                            newScope.getAllMails();
                            newScope.mailHist = true;

                        }, function (response)
                        {
                            window.alert("Failed to send mail!");
                        });



                };

            //Pagination
            newScope.currentPage = 1;
            newScope.itemsPerPage = 5;






        };

        vm.openMailHistoryDialog = function(){


            var newScope = $scope.$new();


            //--------------------------------------------------------------------------

            var dialogOpts = {
                scope: newScope,
                templateUrl: '/views/passenger/mailHistoryModal.html',
                backdrop: 'static',
                size: 'lg'
            };


            var instance = $uibModal.open(dialogOpts);

            newScope.closeDialog = function(){
                instance.close();
                newScope.$destroy();
            };


            newScope.mails = {};


            newScope.getAllMails = function(){
                console.log("getallMail function called");
                var url = "/mail/all";
                var mailPromise = $http.get(url);
                mailPromise.then(function(response){
                    newScope.mails = response.data;
                    console.log("Successfully got mail data", newScope.mails);
                });
            };

                newScope.getAllMails();



            //Pagination
            newScope.currentPage = 1;
            newScope.itemsPerPage = 5;



        }

        vm.openComplaintResolveDialog = function(cObj){


            var newScope = $scope.$new();


            //--------------------------------------------------------------------------

            var dialogOpts = {
                scope: newScope,
                templateUrl: '/views/passenger/resolveUIModal.html',
                backdrop: 'static',
                size: 'sm'
            };


            var instance = $uibModal.open(dialogOpts);

            newScope.closeDialog = function(){
                instance.close();
                newScope.$destroy();
            };


            newScope.com = {
                resolveValue : ""
            };

            newScope.editComplaint = function(){
                console.log("cObj", cObj);
                console.log("newScope.com.resolveValue ", newScope.com.resolveValue);

                var url = "/complainss/update/" + cObj.id;

                var data = {
                    passengerName :cObj.passengerName,
                    pemail :cObj.pemail,
                    complainDate : cObj.complainDate,
                    complainDesc :cObj.complainDesc,
                    complainStatus : newScope.com.resolveValue

                };

                console.log("saving frontend data ", data);

                $http.post(url, data).then(function (response) {

                    window.alert("Successfully Updated!");
                    vm.complaints = response.data;
                    getAll();
                    newScope.closeDialog();

                }, function (response)
                {
                    window.alert("Failed to update complaint!");
                });

            };





        }


     }

})();


