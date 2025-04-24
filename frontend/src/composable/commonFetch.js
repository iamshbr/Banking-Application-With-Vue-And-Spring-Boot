

async function commonFetch(url, method, headers = {}, body = {}) {
  const requestOptions = {
    method: method,
    headers: headers,
    body: body && Object.keys(body).length !== 0 ? JSON.stringify(body) : null,
  };

  let response = null;
  try {
    response = await fetch(url, requestOptions);
    if (!response.ok) {
      throw new Error(`Request failed with status ${response.status}`);
    }
  } catch (error) {
    console.error('Error:', error.message);
    throw error;
  } finally {
    return response;
  }
}

export default commonFetch;