(function () {
    'use strict';

    angular
        .module('app')
        .controller('CarsController', CarsController);

    CarsController.$inject = ['$http'];

    function CarsController($http) {
        var vm = this;

        vm.cars = [];
        vm.getAll = getAll;
        vm.getAffordable = getAffordable;
        vm.deleteCar = deleteCar;

        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/cars/allCars";
            var carsPromise = $http.get(url);
            carsPromise.then(function(response) {
                vm.cars = response.data;
            });
        }

        function getAffordable(){
            var url = "/cars/affordableCars/" + 8000;
            var carsPromise = $http.get(url);
            carsPromise.then(function(response) {
                vm.cars = response.data;
            });
        }

        function deleteCar(id){
            var url = "/cars/deleteCar/" + id;
            $http.post(url).then(function(response) {
                vm.cars = response.data;
            });
        }
    }
})();
