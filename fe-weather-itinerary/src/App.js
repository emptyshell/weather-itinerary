import { Input, Table, Button, PageHeader, notification, Form, Space, Divider, Steps } from 'antd'
import { useState } from 'react';
import './App.css';
import { fetchCityWeather, addItinerary, generateSummary, getItinerary } from './HttpUtils';

function App() {

  const { Search } = Input;
  const [form] = Form.useForm();
  const [data, setData] = useState([]);
  const [itinerary, setItinerary] = useState([]);
  const columns = [
    {
      title: 'City Name',
      dataIndex: 'cityName',
      // specify the condition of filtering result
      // here is that finding the name started with `value`
      onFilter: (value, record) => record.city_name.indexOf(value) === 0,
      sorter: (a, b) => a.cityName.length - b.cityName.length,
      sortDirections: ['descend'],
    },
    {
      title: 'Country Code',
      dataIndex: 'countryCode',
      defaultSortOrder: 'descend',
      sorter: (a, b) => a.countryCode - b.countryCode,
    },
    {
      title: 'Temperature',
      dataIndex: 'temperature',
      onFilter: (value, record) => record.temperature.indexOf(value) === 0,
    },
    {
      title: 'Clouds',
      dataIndex: 'clouds',
      defaultSortOrder: 'descend',
      sorter: (a, b) => a.clouds - b.clouds,
    },
    {
      title: 'Timestamp',
      dataIndex: 'timestamp',
      defaultSortOrder: 'descend',
      sorter: (a, b) => a.timestamp - b.timestamp,
    },
  ];

  const onSearch = value => {
    fetchCityWeather(value).then(response => {
      setData(response.data);
    });
    //todo handle error
  };

  const onSearchItinerary = value => {
    getItinerary(value).then(response => setItinerary(response.data));
    //todo handle error
  };

  const openNotification = (message) => {
    notification.open({
      message: 'Summary',
      description: message,
      placement: 'bottomLeft',
    });
  };

  return (
    <div className="App">
      <PageHeader
        className="site-page-header"
        title="Weather-Itinerary"
      />
      <Form
        form={form}
        layout="vertical"
        autoComplete="off"
      >
        <div style={{ overflow: 'hidden', padding: '20px' }}>
          <Form.Item
            name="cityName"
            label="City name"
          >
            <Input placeholder="City name" />
          </Form.Item>
          <Form.Item
            name="itineraryName"
            label="Itinerary name"
          >
            <Input placeholder="Add itinerary name" />
          </Form.Item>
        </div>
        <Form.Item>
          <Space>
            <Button type="primary" htmlType="submit" onClick={(e) => {
              onSearch(form.getFieldValue('cityName'));
            }}>
              Search
            </Button>
            <Button type="primary" htmlType="button" onClick={(e) => {
              onSearch(form.getFieldValue('cityName'));
              addItinerary(form.getFieldValue('itineraryName'), form.getFieldValue('cityName'))
                .then(
                  () => getItinerary(form.getFieldValue('itineraryName')).then(response => setItinerary(response.data))
                );
            }}>
              Add Itinerary
            </Button>
            <Button type="primary" onClick={() => {
              generateSummary(form.getFieldValue('itineraryName')).then(response => openNotification(response.data))
            }} block>
              Generate Summary
            </Button>
          </Space>
        </Form.Item>
      </Form>
      <Divider orientation="left">Itinerary</Divider>
      <Steps style={{ width: '500px', margin: 'auto', marginBottom: '35px' }}>
        {itinerary.map(item => (<Steps.Step status='finish' key={item.name} title={item.city} subTitle={item.name} />))}
      </Steps>
      <Search
        placeholder="search itinerary name"
        allowClear
        enterButton="Search Itinerary"
        size="large"
        onSearch={onSearchItinerary}
        style={{ width: '500px' }}
      />
      <Table style={{ padding: '20px', }} columns={columns} dataSource={data} />
    </div>
  );
}

export default App;
