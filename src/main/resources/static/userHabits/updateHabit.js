angular.module('tracker-front').controller('updateHabitController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.updateHabit = function () {

        $http.post(contextPath + 'habits/update', $scope.upHabit)
            .then(function (response) {
                window.location.href = contextPath + '#!/myHabits';
            });
    };

    $scope.frequency = [
        {id: 1, name: 'DAILY'},
        {id: 2, name: 'WEEKLY'},
        {id: 3, name: 'BIWEEKLY'},
        {id: 4, name: 'TRIWEEKLY'},
        {id: 5, name: 'MONTHLY'},
    ];

});