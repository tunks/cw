/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.solr;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 *
 * @author ebrimatunkara
 */
public class CustomHttpSolrClient extends HttpSolrClient {

    private static final long serialVersionUID = 1973496789131415484L;

    public CustomHttpSolrClient(String baseURL,
            HttpClient client,
            ResponseParser parser) {
        super(baseURL, client, parser);
    }

    public CustomHttpSolrClient(String baseURL, HttpClient client) {
        super(baseURL, client);
    }

    public CustomHttpSolrClient(String baseURL) {
        super(baseURL);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected HttpRequestBase createMethod(SolrRequest request,
            String collection) throws IOException, SolrServerException {
        String col = (collection != null && baseUrl.endsWith(collection)) ? null : collection;
        return super.createMethod(request, col);
    }
}
