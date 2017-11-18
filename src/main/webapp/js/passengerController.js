(function() {
    'use strict';

    angular
        .module('app')
        .controller('passengerController', Controller);

    Controller.$inject = ['$http','$scope','$uibModal'];

    function Controller($http,$scope,$uibModal) {

        console.log("passenger controller");



        var vm = this;

        vm.passengers = [];

        vm.getAll = getAll;
        vm.deletePassenger = deletePassenger;

        init();

        function init(){
            getAll();
        }


        //Pagination
        vm.currentPage = 1;
        vm.itemsPerPage = 5;



        function getAll(){
            console.log("getall function called");
            var url = "/passenger/all";
            var passengerPromise = $http.get(url);
            passengerPromise.then(function(response){
                vm.passengers = response.data;
                console.log("Successfully got passenger data", vm.passengers);
            });
        }



        function deletePassenger(id){
            var url = "/passenger/delete/" + id;
            $http.post(url).then(function(response){
                vm.passengers = response.data;
            });

        }


        // vm.today = function() {
        //     vm.pdob = new Date();
        // };
        // vm.today();
        //
        // vm.clear = function() {
        //     vm.pdob = null;
        // };

        vm.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
        };

        vm.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yy',
            maxDate: new Date(2020, 5, 22),
            minDate: new Date(),
            startingDay: 1
        };

        // Disable weekend selection
        function disabled(data) {
            var date = data.date,
                mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }

        vm.toggleMin = function() {
            vm.inlineOptions.minDate = vm.inlineOptions.minDate ? null : new Date();
            vm.dateOptions.minDate = vm.inlineOptions.minDate;
        };

        vm.toggleMin();

        vm.open1 = function() {
            vm.popup1.opened = true;
        };

        vm.open2 = function() {
            vm.popup2.opened = true;
        };

        vm.setDate = function(year, month, day) {
            vm.incomeDate = new Date(year, month, day);
        };

        vm.formats = ['yyyy-MM-dd'];
        vm.format = vm.formats[0];
        vm.altInputFormats = ['M!/d!/yyyy'];

        vm.popup1 = {
            opened: false
        };

        vm.popup2 = {
            opened: false
        };

        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var afterTomorrow = new Date();
        afterTomorrow.setDate(tomorrow.getDate() + 1);
        vm.events = [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];

        function getDayClass(data) {
            var date = data.date,
                mode = data.mode;
            if (mode === 'day') {
                var dayToCheck = new Date(date).setHours(0,0,0,0);

                for (var i = 0; i < vm.events.length; i++) {
                    var currentDay = new Date(vm.events[i].date).setHours(0,0,0,0);

                    if (dayToCheck === currentDay) {
                        return vm.events[i].status;
                    }
                }
            }

            return '';
        }
//-------------------------------------------------------------------------------------------

        //open create new setting field template modal
        vm.openCreateDialog = function(){
            vm.openCreateNewDialog(false, 0);
        };

        //open view and update dialog
        vm.openViewUpdateDialog = function(pObj){
            vm.openCreateNewDialog(true, pObj); //pass id of the selected document
        };

        //Create and Update dialog
        vm.openCreateNewDialog = function(isOnViewMode,pObj){



            var newScope = $scope.$new();

            newScope.isOnViewMode = isOnViewMode;


            //--------------------------------------------------------------------------

            var dialogOpts = {
                scope: newScope,
                templateUrl: '/views/passenger/passengerUIModal.html',
                backdrop: 'static',
                size: 'lg'
            };


            var instance = $uibModal.open(dialogOpts);

            newScope.closeDialog = function(){
                instance.close();
                newScope.$destroy();
            };

            newScope.pass = {};

            newScope.ttt = false;

            //-----------EDIT MODE-------------------------------------------------------

            if (newScope.isOnViewMode) {

                newScope.ttt = true;

                var date1 = new Date(pObj.pdob);

                newScope.pass={
                    pnic : pObj.pnic,
                    pname : pObj.pname,
                    paddress : pObj.paddress,
                    pmobile : parseInt(pObj.pmobile),
                    pgender : pObj.pgender,
                    pdob : date1

                };

                console.log("pObj",pObj);
                console.log("date1",date1);

                newScope.editPassenger= function() {

                    console.log("save function called");


                    var url = "/passenger/update/" + pObj.id;

                    var data = {

                        pnic: newScope.pass.pnic,
                        pname: newScope.pass.pname,
                        paddress: newScope.pass.paddress,
                        pmobile: newScope.pass.pmobile,
                        pgender:newScope.pass.pgender,
                        pdob: newScope.pass.pdob

                    };

                    console.log("saving frontend data ", data);

                    $http.post(url, data).then(function (response) {

                        window.alert("Successfully Updated!");
                        vm.passengers = response.data;
                        getAll();
                        newScope.closeDialog();

                    }, function (response)
                    {
                        window.alert("Failed to update passenger!");
                    });



                };


            }

            //-------------------SAVE MODE-------------------------------------------------

            if (!newScope.isOnViewMode) {

                newScope.pass={
                    pnic : "",
                    pname :"",
                    paddress : "",
                    pmobile : "",
                    pgender : "",
                    pdob : ""

                };


                newScope.savePassenger= function() {

                    console.log("save function called");


                        var url = "/passenger/save";

                        var data = {

                            pnic: newScope.pass.pnic,
                            pname: newScope.pass.pname,
                            paddress: newScope.pass.paddress,
                            pmobile: newScope.pass.pmobile,
                            pgender:newScope.pass.pgender,
                            pdob: newScope.pass.pdob

                        };

                        console.log("saving frontend data ", data);

                        $http.post(url, data).then(function (response) {

                            window.alert("Successfully Saved!");
                            vm.passengers = response.data;
                            getAll();
                            newScope.closeDialog();

                        }, function (response)
                        {
                            window.alert("Failed to save passenger!");
                        });



                };

            }

            //----------DatePicker---------------------------------------------
            //
            // newScope.today = function() {
            //     newScope.pass.pdob = new Date();
            // };
            // newScope.today();
            //
            // newScope.clear = function() {
            //     newScope.pass.pdob = null;
            // };

            newScope.inlineOptions = {
                customClass: getDayClass,
                minDate: new Date(),
                showWeeks: true
            };

            newScope.dateOptions = {
                dateDisabled: disabled,
                formatYear: 'yy',
                maxDate: new Date(2020, 5, 22),
                minDate: new Date(),
                startingDay: 1
            };

            // Disable weekend selection
            newScope.disabled = function(data) {
                var date = data.date,
                    mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            };

            newScope.toggleMin = function() {
                newScope.inlineOptions.minDate = newScope.inlineOptions.minDate ? null : new Date();
                newScope.dateOptions.minDate = newScope.inlineOptions.minDate;
            };

            newScope.toggleMin();

            newScope.open1 = function() {
                newScope.popup1.opened = true;
            };

            newScope.open2 = function() {
                newScope.popup2.opened = true;
            };

            newScope.setDate = function(year, month, day) {
                newScope.incomeDate = new Date(year, month, day);
            };

            newScope.formats = ['yyyy-MM-dd'];
            newScope.format = newScope.formats[0];
            newScope.altInputFormats = ['M!/d!/yyyy'];

            newScope.popup1 = {
                opened: false
            };

            newScope.popup2 = {
                opened: false
            };

            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            var afterTomorrow = new Date();
            afterTomorrow.setDate(tomorrow.getDate() + 1);
            vm.events = [
                {
                    date: tomorrow,
                    status: 'full'
                },
                {
                    date: afterTomorrow,
                    status: 'partially'
                }
            ];

            newScope.getDayClass=function(data) {
                var date = data.date,
                    mode = data.mode;
                if (mode === 'day') {
                    var dayToCheck = new Date(date).setHours(0,0,0,0);

                    for (var i = 0; i < newScope.events.length; i++) {
                        var currentDay = new Date(newScope.events[i].date).setHours(0,0,0,0);

                        if (dayToCheck === currentDay) {
                            return newScope.events[i].status;
                        }
                    }
                }

                return '';
            };


            //VALIDATIONS
            //--------------------------------------------------------------------------

            newScope.validgrouppname = function () {

                newScope.vall = 0;


                vm.passengers.forEach(function (passenger) {
                    if (passenger.pnic === newScope.pass.pnic) {
                        newScope.vall = 1;
                        return false;
                    }
                });


                if (newScope.isOnViewMode) {
                    newScope.vall = 0;
                }

            };

            newScope.isValidData = function () {
                if ((newScope.pass.pnic !== '') ) {

                    if (newScope.vall === 0) {

                        return true;
                    }
                }
            };


        }


    }

})();


