package com.example.springrestotot;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuoteRepository extends JpaRepository<Quote, Long> {
}
