angular.module('tracker-front').controller('userHabitController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';


    $scope.count_pages = 2;
    $scope.newHabit = null;

    $scope.loadUserHabits = function () {
        console.log($localStorage.springWebUser.username);
        $http({
            url: contextPath + 'habits/' + $localStorage.springWebUser.username,
            method: 'GET'
        }).then(function (response) {
            console.log(response.data)
            $scope.userHabitList = response.data;
        });
    };



    $scope.showCurrentUserInfo = function (){
        $http({
            url: contextPath + 'users/about_me',
            method: 'GET'
        }).then(function (response){
            $scope.aboutMe = response.data;
        });
    };


    $scope.deleteHabit = function (habitId){
        $http({
            url: contextPath + 'habits/',
            method: 'DELETE',
            params: {
                habitId: habitId,
                username: $localStorage.springWebUser.username
            }
        }).then(function (response){
            $scope.loadUserHabits();
        });
    };

    $scope.showUpdatePage = function (name){
        $http({
            url: contextPath + 'habits/update/',
            method: 'GET',
            params: {
                name: name,
                username: $localStorage.springWebUser.username
            }
        }).then(function (response){
            $scope.upHabit = response.data;
            window.location.href = contextPath + '#!/updateHabit';
        });

    };

    $scope.addHabit = function () {
        $scope.newHabit.username = $localStorage.springWebUser.username;
        $scope.newHabit.currentQuantity = 0;
        $http.post(contextPath + 'habits', $scope.newHabit)
            .then(function (response) {
                window.location.href = contextPath + '#!/myHabits';
                $scope.loadUserHabits();
            });
    };

    $scope.loadUserHabits();

});