var demo = angular.module('demo', []);

demo
    .controller('SalaryController', function ($scope, $http) {
        $http.get('http://localhost:8080/countries/').then(function (response) {
            $scope.countries = response.data;
        });

        $scope.onSubmit = function () {
            var id = $scope.country.id;
            var dailySalary = $scope.dailySalary;
            var url = 'http://localhost:8080/salary/' + id + '/' + dailySalary;


            $http.get(url).then(function (response) {
                $scope.salary = response.data;
                $scope.countryForm = {};
            })
        };


    });