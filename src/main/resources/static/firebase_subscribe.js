// firebase_subscribe.js
firebase.initializeApp({
    //senderId из настроек проекта. его передаст на firebase и по нему получит токен.
    // отправалять из другого проекта на этот токен не получится, firebase ругнётся что он для другого получатея
    messagingSenderId: '668901969584'
});

// браузер поддерживает уведомления ?
// вообще, эту проверку должна делать библиотека Firebase, но она этого не делает
if ('Notification' in window) {
    var messaging = firebase.messaging();

    // запрашиваем у пользователя разрешение на уведомления
    // и подписываем его
    subscribe();
}

function subscribe() {
    console.log('Начинаем процедуру подписки');

    // запрашиваем разрешение на получение уведомлений
    messaging.requestPermission()
        .then(function () {
            // получаем ID устройства
            messaging.getToken()
                .then(function (currentToken) {
                    console.log(currentToken);

                    if (currentToken) {
                        sendTokenToServer(currentToken);
                    } else {
                        console.warn('Не удалось получить токен.');
                        setTokenSentToServer(false);
                    }
                })
                .catch(function (err) {
                    console.warn('При получении токена произошла ошибка.', err);
                    setTokenSentToServer(false);
                });
        })
        .catch(function (err) {
            console.warn('Не удалось получить разрешение на показ уведомлений.', err);
        });
}

// отправка ID на сервер
function sendTokenToServer(currentToken) {
    console.log('Отправка токена на сервер...');

    var url = 'http://localhost/subscribe'; // адрес скрипта на сервере который сохраняет ID устройства
    $.post(url, {
        token: currentToken
    });

    console.log('Токен успешно отправлен на сервер');
}