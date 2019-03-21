<?php
/**
*数据库连接操作
*/
class Db
{
    static private $_instance;
    static private $_connectSource;
    private $_dbConfig = array(
        'host' => 'localhost',
        'user' => 'root',
        'password' => '123',
        'database' => 'page',
    );

    function __construct()
    {

    }

    static public function getInstance()
    {
        if (!self::$_instance instanceof self) {
            self::$_instance = new self();
        }
        return self::$_instance;
    }

    public function connect()
    {
        if (!self::$_connectSource) {
            self::$_connectSource =@mysqli_connect($this->_dbConfig['host'], $this->_dbConfig['user'], $this->_dbConfig['password']);
            if (!self::$_connectSource) {
                throw new Exception('mysql connect error ' . mysqli_error());
                //die('mysql connect error' . mysqli_error());
            }
            mysqli_select_db(self::$_connectSource,$this->_dbConfig['database']);
            mysqli_query( self::$_connectSource,"set names UTF8");
        }
        return self::$_connectSource;
    }
}
