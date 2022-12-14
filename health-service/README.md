# Задание

Создать минимальный сервис, который

- отвечает на порту 8000
- имеет
  http-метод GET /health/
  RESPONSE: {"status": "OK"}
- Cобрать локально образ приложения в докер.
- Запушить образ в dockerhub
- Написать манифесты для деплоя в k8s для этого сервиса.
- Манифесты должны описывать сущности Deployment, Service, Ingress.
- В Deployment могут быть указаны Liveness, Readiness пробы.
- Количество реплик должно быть не меньше 2. Image контейнера должен быть указан с Dockerhub.
- Хост в ингрессе должен быть arch.homework. В итоге после применения манифестов GET запрос на http://arch.homework/health  должен отдавать {“status”: “OK”}.

На выходе предоставить
- ссылку на github c манифестами. Манифесты должны лежать в одной директории, так чтобы можно было их все применить одной командой kubectl apply -f .
- url, по которому можно будет получить ответ от сервиса (либо тест в postmanе).

Задание со звездой* (+5 баллов):
В Ingress-е должно быть правило, которое форвардит все запросы с /otusapp/{student name}/* на сервис с rewrite-ом пути. Где {student name} - это имя студента.

# Решение

Реализовано через helm, чтобы не писать манифесты вручную.

Если нужны, они сгенерированы через `helm template` и лежат тут [helm/out/manifests.yml](helm/out/manifests.yml)

применить можно так

```shell
helm install --atomic heath-srv helm/
```

или так

```shell
kubectl apply -f helm/out/manifests.yml
```

проверить c path rewrite

```shell
curl http://arch.homework/otusapp/alex-v/health
```