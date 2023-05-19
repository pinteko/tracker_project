angular.module('tracker-front').controller('habitController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';


    $scope.count_pages = 2;

    $scope.loadBooks = function (page, min_rating, max_rating,
                                 min_price, max_price, title_part, names, surname) {
        $http({
            url: contextPath + 'novels',
            method: 'GET',
            params: {
                p: page,
                min_rating: min_rating,
                max_rating: max_rating,
                min_price: min_price,
                max_price: max_price,
                title_part: title_part,
                names: names,
                surname: surname
            }
        }).then(function (response) {
            $scope.bookList = response.data.content;
            console.log($scope.bookList);
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


    $scope.deleteHabit = function (novel_id){
        $http({
            url: contextPath + 'novels/edit/delete_novel',
            method: 'DELETE',
            params: {
                novel_id: novel_id,
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

    $scope.createHabit = function () {
        console.log($scope.newNovel);
        console.log($scope.author);
        $http.post(contextPath + 'novels', $scope.newNovel)
            .then(function (response) {
                window.location.href = contextPath + 'novels.html';
                $scope.loadBooks();
            });
    };

    $scope.loadBooks();

});