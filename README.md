# Лабораторная работа №4

### 1. Цель работы.

Изучение применимости брокеров сообщений для построения распределённой информационной системы.

### 2. Теоретическая часть.
RabbitMQ — программный брокер сообщений[en] на основе стандарта AMQP — тиражируемое связующее программное обеспечение, ориентированное на обработку сообщений. Создан на основе системы Open Telecom Platform, написан на языке Erlang, в качестве движка базы данных для хранения сообщений использует Mnesia.

Очередь сообщений некая структура данных, которая обеспечивает хранение и передачу двоичных данных (blobs) между различными участниками системы. Очереди сообщений практически всегда используются в крупных системах, благодаря важным преимуществам.

Независимость компонентов системы друг от друга. Благодаря использованию очереди, компоненты взаимодействуют через некий общий интерфейс, но ничего не знают о существовании друг друга.
Экономия ресурсов достигается вследствие возможности разумно распределять информацию, поступающую в очередь от одних процессов, между другими процессами, осуществляющими её обработку. Кроме того, благодаря тому, что нет необходимости промежуточного хранения необработанных данных, достигается дополнительная экономия ресурсов.
Надежность очередей достигается благодаря возможности накапливать сообщения, амортизируя недостаток вычислительных возможностей системы, а также благодаря независимости компонентов. Помимо этого очередь может аккомодировать сбои отдельных компонентов, осуществляя доставку «опоздавших» сообщений после восстановления.
И, наконец, гарантия последовательной обработки, позволяющая точно контролировать потоки данных в системе и запускать асинхронную обработку там, где это необходимо, не беспокоясь, что одна операция выполнится раньше другой, от результата которой она зависит.

[RabbitMQ](https://ru.wikipedia.org/wiki/RabbitMQ) [Очереди сообщений](https://makeomatic.ru/blog/2013/10/16/RabbitMQ/)

### 3. Ход выполнения работы.



### 4. Вывод.
