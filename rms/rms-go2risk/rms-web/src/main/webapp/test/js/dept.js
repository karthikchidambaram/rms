angular.module('dept', [])
.controller('Dept', function($scope, $http) {
    $http.get('http://localhost:8088/rmsrest/p/test/department/10').
        then(function(response) {
            $scope.deptDtls = response.data;
        });
});
